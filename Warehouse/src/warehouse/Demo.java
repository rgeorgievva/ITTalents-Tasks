package warehouse;


import java.util.ArrayList;

public class Demo {

    public static final int NUMBER_SHOPS = 3;
    public static final int AVAILABILITY_MINIMUM = 15;
    public static final int NUMBER_CLIENTS = 9;

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        Supplier.warehouse = warehouse;
        Shop.warehouse = warehouse;

        Supplier supplier = new Supplier();
        supplier.start();

        ArrayList<Shop> shops = new ArrayList<>();

        for (int i = 0; i < NUMBER_SHOPS; i++) {
            Shop shop = new Shop("Shop" + (i + 1), AVAILABILITY_MINIMUM);
            shops.add(shop);
            shop.start();
        }


        for (int i = 0; i < NUMBER_CLIENTS; i++) {
            Shop shop = null;
            if (i < 3) {
                shop = shops.get(0);
            }
            if (i >=3 && i < 6) {
                shop = shops.get(1);
            }
            if (i >= 6) {
                shop = shops.get(2);
            }
            Client client = new Client("Client" + (i + 1), shop);
            client.start();
        }

    }
}
