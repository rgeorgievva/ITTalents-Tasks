package agency;

import agency.client.Buyer;
import agency.client.Seller;

import java.util.HashSet;

public class Agent {

    private String name;
    private String mobileNumber;
    private HashSet<Seller> sellers;
    private HashSet<Buyer> buyers;
    private HashSet<View> views;
    private double money;

    public Agent(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.sellers = new HashSet<>();
        this.buyers = new HashSet<>();
        this.views = new HashSet<>();
        this.money = 0.0;
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }

    public void addBuyer(Buyer buyer) {
        this.buyers.add(buyer);
    }

    public void addView(View view) {
        this.views.add(view);
    }

    public void receiveMoney(double amount) {
        if (amount > 0) {
            this.money += amount;
        }
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String  getName() {
        return name;
    }
}
