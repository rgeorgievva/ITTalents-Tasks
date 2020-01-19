package agency.real.estate;

import agency.Agent;
import agency.client.Seller;

public abstract class RealEstate {

    public enum Type {

        APARTMENT, HOUSE, PARCEL
    }

    interface Kind {

    }

    private String description;
    private String address;
    private double price;
    private double area;
    private Agent agent;
    private Type type;
    private Kind kind;
    private Seller seller;

    public RealEstate(String description, String address, double price, double area, Type type, Kind kind) {
        this.description = description;
        this.address = address;
        this.price = price;
        this.area = area;
        this.type = type;
        this.kind = kind;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "RealEstate{" +
                "description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", agent=" + agent +
                ", type=" + type +
                ", kind=" + kind +
                ", seller=" + seller +
                '}';
    }
}
