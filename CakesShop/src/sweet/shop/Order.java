package sweet.shop;

import sweet.shop.cake.Cake;
import sweet.shop.client.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    private Client client;
    private double price;
    private String deliveryAddress;
    private ArrayList<Cake> cakes;
    private LocalDateTime orderDeliveryTime;
    private Supplier supplier;

    public Order(Client client, double price, String deliveryAddress, ArrayList<Cake> cakes,
                 LocalDateTime orderDeliveryTime, Supplier supplier) {
        this.client = client;
        this.price = price;
        this.deliveryAddress = deliveryAddress;
        this.cakes = cakes;
        this.orderDeliveryTime = orderDeliveryTime;
        this.supplier = supplier;
    }
}
