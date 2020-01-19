package harbour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Harbour {
    
    private static final int NUMBER_DOCKS = 5;
    private static final int NUMBER_WAREHOUSES = 2;


    private ArrayList<Dock> docks;
    private ArrayList<Warehouse> warehouses;
    private Vector<PortShipment> harbourLog;


    public Harbour() {
        this.docks = new ArrayList<>(NUMBER_DOCKS);
        for (int i = 0; i < NUMBER_DOCKS; i++) {
            this.docks.add(new Dock("Dock" + (i + 1)));
        }
        
        this.warehouses = new ArrayList<>(NUMBER_WAREHOUSES);
        for (int i = 0; i < NUMBER_WAREHOUSES; i++) {
            this.warehouses.add(new Warehouse("Warehouse" + (i + 1)));

        }
        this.harbourLog = new Vector<>();
    }

    private Dock getRandomDock() {
        return this.docks.get(new Random().nextInt(this.docks.size()));
    }
    
    public void addShip(Ship ship) {
        Dock dock = getRandomDock();
        dock.shipArrival(ship);
    }


    public void unloadShip(Crane unloadingCrane) {
        for (Dock dock : this.docks) {
            if (dock.getNumberShips() != 0) {
                Ship ship = dock.removeShip();
                System.out.println(unloadingCrane.getName() + " is unloading " + ship.getName() + " on dock " +
                        dock.getName() + ".Unloaded parcels number: " + ship.getParcels().size());
                addParcelsToWarehouse(ship.getParcels(), dock, ship, unloadingCrane);
            }
        }
    }

    public void addParcelsToWarehouse(List<Parcel> parcels, Dock fromDock, Ship fromShip, Crane fromCrane) {
        Warehouse warehouse = this.warehouses.get(new Random().nextInt(2));
        for (Parcel parcel : parcels) {
            System.out.println("Parcel added to warehouse! Current number parcels" + warehouse.getNumberParcels());
            warehouse.addParcel(parcel);
            PortShipment portShipment = new PortShipment(parcel.getId(), fromDock.getId(), fromCrane.getCraneId(),
                    fromShip.getName());
            harbourLog.add(portShipment);
            try {
                PortShipmentDAO.getInstance().saveParcelInfo(portShipment);
            } catch (SQLException e) {
                System.out.println("Error saving parcel information to DB: " + e.getMessage());
            }
        }
    }
}
