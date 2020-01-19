package postOffice.packets;

import postOffice.people.Citizen;

public class Parcel extends Packet {

    private static final double INCREASING_PERCENTAGE = 50;

    public static final int PARCEL_TAX = 2;
    public static final int NORMAL_PARCEL_DIMENSION = 60;
    public static final int PARCEL_DELIVERY_TIME = 1500;
    private int length;
    private int width;
    private int height;
    private boolean isFragile;

    public Parcel(Citizen sender, Citizen receiver, int length, int width, int height, boolean isFragile) {
        super(sender, receiver);
        this.length = length;
        this.width = width;
        this.height = height;
        calculateTax();
        this.isFragile = isFragile;
    }

    private void calculateTax() {
        this.tax = PARCEL_TAX;

        if (height > NORMAL_PARCEL_DIMENSION || width > NORMAL_PARCEL_DIMENSION || length > NORMAL_PARCEL_DIMENSION) {
            tax += tax * (INCREASING_PERCENTAGE / 100);
        }

        if (isFragile) {
            tax += tax * (INCREASING_PERCENTAGE / 100);
        }
    }


    @Override
    public int getDeliveryTime() {
        return PARCEL_DELIVERY_TIME;
    }
}
