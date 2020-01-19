package harbour;

public class Distributor extends Thread {

    private Warehouse warehouse;

    public Distributor(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            this.warehouse.distributeParcels();
        }
    }
}
