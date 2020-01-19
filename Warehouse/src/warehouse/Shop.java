package warehouse;

import warehouse.product.Fruit;
import warehouse.product.Meat;
import warehouse.product.Product;
import warehouse.product.Vegetable;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends Thread {

    public static Warehouse warehouse;

    private HashMap<Product.Type, HashMap<Product.Name, ArrayList<Product>>> availableProducts;
    private int availabilityMinimum;

    public Shop(String name, int availabilityMinimum) {
        super(name);
        this.availableProducts = new HashMap<>();
        this.availabilityMinimum = availabilityMinimum;
        this.addProducts(new Fruit(Fruit.Name.BANANA), availabilityMinimum);
        this.addProducts(new Fruit(Fruit.Name.ORANGE), availabilityMinimum);
        this.addProducts(new Fruit(Fruit.Name.APPLE), availabilityMinimum);
        this.addProducts(new Vegetable(Vegetable.Name.PATATO), availabilityMinimum);
        this.addProducts(new Vegetable(Vegetable.Name.EGGPLANT), availabilityMinimum);
        this.addProducts(new Vegetable(Vegetable.Name.CUCUMBER), availabilityMinimum);
        this.addProducts(new Meat(Meat.Name.PORK), availabilityMinimum);
        this.addProducts(new Meat(Meat.Name.BEEF), availabilityMinimum);
        this.addProducts(new Meat(Meat.Name.CHICKEN), availabilityMinimum);
    }

    @Override
    public void run() {
        while (true) {
            deliverProducts();
        }
    }

    private void deliverProducts() {
        for (Product.Type type : this.availableProducts.keySet()) {
            for (Product.Name name : this.availableProducts.get(type).keySet()) {
                if (this.availableProducts.get(type).get(name).size() <= this.availabilityMinimum) {
                    warehouse.sellStock(this.availableProducts.get(type).get(name).get(0), this);
                }
            }
        }
    }

    public void sellProducts(Product product, int quantity) {
        synchronized (Warehouse.lock) {
            try {
                while (getAvailability(product) < availabilityMinimum) {
                    Warehouse.lock.wait();
                }

                System.out.println("Client buys " + quantity + " " + product.getName() + " from shop " + this.getName());
                for (int i = 0; i < quantity; i++) {
                    this.availableProducts.get(product.getType()).get(product.getName()).remove(0);
                }
                Warehouse.lock.notifyAll();
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
        }
    }

    public void addProducts(Product product, int number) {
        if (!this.availableProducts.containsKey(product.getType())) {
            this.availableProducts.put(product.getType(), new HashMap<>());
        }

        if (!this.availableProducts.get(product.getType()).containsKey(product.getName())) {
            this.availableProducts.get(product.getType()).put(product.getName(), new ArrayList<>());
        }

        for (int i = 0; i < number; i++) {
            this.availableProducts.get(product.getType()).get(product.getName()).add(product);
        }
    }

    private int getAvailability(Product product) {
        if (!this.availableProducts.containsKey(product.getType())) {
            return 0;
        }

        if (!this.availableProducts.get(product.getType()).containsKey(product.getName())) {
            return 0;
        }

        return this.availableProducts.get(product.getType()).get(product.getName()).size();
    }
}
