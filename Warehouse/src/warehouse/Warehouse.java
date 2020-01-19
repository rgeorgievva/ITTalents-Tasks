package warehouse;

import warehouse.product.Fruit;
import warehouse.product.Meat;
import warehouse.product.Product;
import warehouse.product.Vegetable;

import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {

    public static final Object lock = new Object();

    private static final int INITIAL_PRODUCT_AVAILABILITY = 15;
    public static final int DELIVERY_DEFAULT_NUMBER_PRODUCTS = 25;
    public static final int MIN_NUMBER_PRODUCT_AVAILABILITY = 10;

    private HashMap<Product.Type, HashMap<Product.Name, ArrayList<Product>>> availableProducts;

    public Warehouse() {
        this.availableProducts = new HashMap<>();
        this.addProducts(new Fruit(Fruit.Name.BANANA), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Fruit(Fruit.Name.ORANGE), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Fruit(Fruit.Name.APPLE), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Vegetable(Vegetable.Name.PATATO), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Vegetable(Vegetable.Name.EGGPLANT), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Vegetable(Vegetable.Name.CUCUMBER), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Meat(Meat.Name.PORK), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Meat(Meat.Name.BEEF), INITIAL_PRODUCT_AVAILABILITY);
        this.addProducts(new Meat(Meat.Name.CHICKEN), INITIAL_PRODUCT_AVAILABILITY);
    }

    private void addProducts(Product product, int number) {
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

    public void receiveStock() {
        synchronized (lock) {
            try {
                ArrayList<Product> scarceProducts = getScarceProducts();
                while (scarceProducts.size() == 0) {
                    lock.wait();
                    scarceProducts = getScarceProducts();
                }

                for (Product product : scarceProducts) {
                    this.addProducts(product, DELIVERY_DEFAULT_NUMBER_PRODUCTS);
                }
                lock.notifyAll();
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for scarce products");
            }
        }
    }

    public void sellStock(Product product, Shop shop) {
        synchronized (lock) {
            try {
                while (getAvailability(product) < MIN_NUMBER_PRODUCT_AVAILABILITY) {
                    lock.wait();
                }

                System.out.println("Warehouse sells " + product.getName() + " to " + shop.getName());
                shop.addProducts(product, 5);
                this.removeProducts(product);
                lock.notifyAll();
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for a product to be delivered.");
            }
        }
    }

    private ArrayList<Product> getScarceProducts() {
        ArrayList<Product> scarceProducts = new ArrayList<>();

        for (Product.Type type : this.availableProducts.keySet()) {
            for (Product.Name name : this.availableProducts.get(type).keySet()) {
                if (this.availableProducts.get(type).get(name).size() < MIN_NUMBER_PRODUCT_AVAILABILITY) {
                    scarceProducts.add(this.availableProducts.get(type).get(name).get(0));
                }
            }
        }

        return scarceProducts;
    }

    private void removeProducts(Product product) {
        if (!this.availableProducts.containsKey(product.getType())) {
            return;
        }

        if (!this.availableProducts.get(product.getType()).containsKey(product.getName())) {
            return;
        }

        if (this.availableProducts.get(product.getType()).get(product.getName()).size() < 5) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            this.availableProducts.get(product.getType()).get(product.getName()).remove(0);
        }
    }
}
