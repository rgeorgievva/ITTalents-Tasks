package postOffice.packets;

import postOffice.people.Citizen;

public abstract class Packet {

    private Citizen sender;
    private Citizen receiver;
    protected double tax;

    public Packet(Citizen sender, Citizen receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public abstract int getDeliveryTime();

//    public boolean isParcel() {
//        return false;
//    }
//
//    public boolean isLetter() {
//        return true;
//    }
}
