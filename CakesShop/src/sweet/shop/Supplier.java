package sweet.shop;

import java.util.HashSet;

public class Supplier {

    public static SweetShop sweetShop;
    private String name;
    private String mobileNumber;
    private HashSet<Order> orders;
    private double money;

    public Supplier(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.money = 0.0;
        this.orders = new HashSet<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void receiveMoney(double amount, double tip) {
        if (amount > 0) {
            sweetShop.receiveMoney(amount);
            System.out.println(this.name + " receives tip:" + tip);
            this.money += tip;
        }
    }

    public double getMoney() {
        return this.money;
    }

    public int getNumberOrders() {
        return this.orders.size();
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", numberOrders='" + orders.size() + '\'' +
                ", money=" + money +
                '}';
    }
}
