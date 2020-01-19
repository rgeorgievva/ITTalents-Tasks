package sweet.shop.client;

import sweet.shop.Order;
import sweet.shop.Supplier;
import sweet.shop.SweetShop;
import sweet.shop.cake.Cake;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Client {

    public static SweetShop sweetShop;
    private String name;
    private String mobileNumber;
    protected double tipPercentage;
    private double spendMoneyForCakes;

    public Client(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.spendMoneyForCakes = 0.0;
    }

    public abstract boolean isValidCakesNumberOrder(ArrayList<Cake> cakes);

    public abstract double getDiscount(double totalPrice);

    public void orderCake(ArrayList<Cake> cakes, String deliveryAddress) {
        System.out.println("Client " + this.name + " wants to order:");
        Client.sweetShop.addClient(this);

        if (!isValidCakesNumberOrder(cakes)) {
            System.out.println("Invalid number of cakes for order");
            return;
        }

        for (Cake cake : cakes) {
            if (!Client.sweetShop.isCakeAvailable(cake)) {
                System.out.println("Invalid order! Not all cakes are available!");
                return;
            }
        }

        for (Cake cake : cakes) {
            Client.sweetShop.sellCake(cake);
        }

        double totalPrice = 0;
        for (Cake cake : cakes) {
            totalPrice += cake.getPrice();
        }
        System.out.println("Order total price without discount " + totalPrice);

        totalPrice = getDiscount(totalPrice);
        System.out.println("Order total price with discount " + totalPrice);
        double tip = totalPrice * this.tipPercentage;
        System.out.println("Tip: " + tip);
        Supplier supplier = Client.sweetShop.getRandomSupplier();

        Order order = new Order(this, totalPrice, deliveryAddress, cakes, LocalDateTime.now(), supplier);
        supplier.addOrder(order);
        supplier.receiveMoney(totalPrice, tip);

        this.spendMoneyForCakes += (totalPrice + tip);

        System.out.println("Order finished!");
    }

    public double getSpendMoneyForCakes() {
        return spendMoneyForCakes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", spendMoneyForCakes=" + spendMoneyForCakes +
                '}';
    }
}
