package warehouse;

import warehouse.product.Fruit;
import warehouse.product.Meat;
import warehouse.product.Product;
import warehouse.product.Vegetable;
import java.util.Random;

public class Client extends Thread {

    private static Product[] availableProducts = {new Fruit(Fruit.Name.BANANA), new Fruit(Fruit.Name.APPLE),
            new Fruit(Fruit.Name.ORANGE), new Vegetable(Vegetable.Name.PATATO),
            new Vegetable(Vegetable.Name.CUCUMBER), new Vegetable(Vegetable.Name.EGGPLANT), new Meat(Meat.Name.BEEF),
            new Meat(Meat.Name.CHICKEN), new Meat(Meat.Name.PORK)};

    private Shop shop;

    public Client(String name, Shop shop) {
        super(name);
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
                buyProducts();
            } catch (InterruptedException e) {
                System.out.println("Interrupted while sleeping.");
            }
        }
    }

    private void buyProducts() {
        Random r = new Random();
        Product product = availableProducts[r.nextInt(availableProducts.length)];
        int quantity = r.nextInt(3) + 1;
        shop.sellProducts(product, quantity);
    }
}
