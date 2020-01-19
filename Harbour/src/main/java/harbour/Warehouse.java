package harbour;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Warehouse {

    private static final int WAREHOUSE_CAPACITY = 30;

    private String name;
    private BlockingQueue<Parcel> parcels;
    private Distributor distributor;

    public Warehouse(String name) {
        this.name = name;
        this.parcels = new ArrayBlockingQueue<>(WAREHOUSE_CAPACITY);
        this.distributor = new Distributor(this);
        this.distributor.start();
    }

    public int getNumberParcels() {
        return this.parcels.size();
    }

    public String getName() {
        return name;
    }

    public void addParcel(Parcel parcel) {
        try {
            this.parcels.put(parcel);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void distributeParcels() {
        try {
            Thread.sleep(2000);//5 seconds
            this.parcels.take();
            System.out.println("Distributed parcel from " + this.name +  ".Current number parcels: " + this.parcels.size());
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
