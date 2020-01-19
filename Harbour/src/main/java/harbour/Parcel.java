package harbour;

public class Parcel {

    private static final int PARCEL_UNLOADING_TIME = 2;
    private static int currentId = 1;

    private int id;

    public Parcel() {
        this.id = currentId++;
    }

    public int getId() {
        return id;
    }

    public int timeForUnloading() {
        return PARCEL_UNLOADING_TIME;
    }
}
