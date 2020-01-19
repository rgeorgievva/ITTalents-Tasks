package postOffice.packets;

import postOffice.people.Citizen;

public class Letter extends Packet {


    public static final double LETTER_TAX = 0.5;
    public static final int LETTER_DELIVERY_TIME = 1000;

    public Letter(Citizen sender, Citizen receiver) {
        super(sender, receiver);
        this.tax = LETTER_TAX;
    }

    @Override
    public int getDeliveryTime() {
        return LETTER_DELIVERY_TIME;
    }
}
