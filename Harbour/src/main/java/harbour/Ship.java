package harbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ship {

    private String name;
    private ArrayList<Parcel> parcels;

    public Ship(String name) {
        this.name = name;
        addParcels();
    }

    private void addParcels() {
        this.parcels = new ArrayList<>();
        Random r = new Random();
        int numberParcels = r.nextInt(4) + 1;
        for (int i = 0; i < numberParcels; i++) {
            this.parcels.add(new Parcel());
        }
    }

    public int getTimeForUnloading() {
        return parcels.size() * parcels.get(0).timeForUnloading();
    }

    public void arriveAtHarbour(Harbour harbour) {
        harbour.addShip(this);
    }

    public String getName() {
        return name;
    }

    public List<Parcel> getParcels() {
        return Collections.unmodifiableList(this.parcels);
    }
}
