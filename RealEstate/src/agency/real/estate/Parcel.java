package agency.real.estate;

public class Parcel extends RealEstate {

    public enum ParcelKind implements Kind {

        FIELD, MEADOW, FOREST
    }

    private boolean isRegulated;

    public Parcel(String description, String address, double price, double area,
                  ParcelKind kind, boolean isRegulated) {
        super(description, address, price, area, Type.PARCEL, kind);
        this.isRegulated = isRegulated;
    }
}
