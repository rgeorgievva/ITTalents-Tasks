package harbour;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Dock {

    private static final int DOCK_CAPACITY = 5;
    private static int currentId = 1;

    private String name;
    private int id;
    private BlockingQueue<Ship> ships;

    public Dock(String name) {
        this.name = name;
        this.id = currentId++;
        this.ships = new ArrayBlockingQueue<>(DOCK_CAPACITY);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void shipArrival(Ship ship) {
        try {
            System.out.println(ship.getName() + " arrived at " + this.name);
            this.ships.put(ship);
        } catch (InterruptedException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public Ship removeShip() {
        try {
            Ship shipToUnload = this.ships.take();
            Thread.sleep(shipToUnload.getTimeForUnloading() * 1000);
            System.out.println(shipToUnload.getName() + " leaves " + this.name);
            return shipToUnload;
        } catch (InterruptedException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }

    public int getNumberShips() {
        return this.ships.size();
    }

}
