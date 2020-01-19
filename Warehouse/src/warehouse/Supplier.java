package warehouse;

public class Supplier extends Thread {

    public static Warehouse warehouse;

    @Override
    public void run() {
        while (true) {
            loadWarehouse();
        }
    }

    private void loadWarehouse() {
        System.out.println("Supplier loads the warehouse!");
        warehouse.receiveStock();
    }
}
