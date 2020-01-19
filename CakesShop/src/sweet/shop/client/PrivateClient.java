package sweet.shop.client;

import sweet.shop.cake.Cake;

import java.util.ArrayList;

public class PrivateClient extends Client {

    private static final int MIN_NUMBER_VAUCHERS = 1;
    private static final int MAX_NUMBER_VAUCHERS = 4;
    private static final int PRIVATE_MIN_NUMBER_CAKES_ORDER = 1;
    private static final int PRIVATE_MAX_NUMBER_CAKES_ORDER = 3;
    private static final double PRIVATE_TIP_PERCENTAGE = 0.02;


    public ArrayList<Integer> vauchers;

    public PrivateClient(String name, String mobileNumber, ArrayList<Integer> vauchers) {
        super(name, mobileNumber);
        this.tipPercentage = PRIVATE_TIP_PERCENTAGE;
        this.vauchers = vauchers;
    }

    public void setVauchers(ArrayList<Integer> vauchers) {
        if (vauchers.size() >= MIN_NUMBER_VAUCHERS && vauchers.size() <= MAX_NUMBER_VAUCHERS) {
            this.vauchers = vauchers;
        }
        else {
            System.out.println("INCORRECT NUMBER OF VAUCHERS!");
            System.exit(0);
        }
    }

    @Override
    public boolean isValidCakesNumberOrder(ArrayList<Cake> cakes) {
        if (cakes.size() < PRIVATE_MIN_NUMBER_CAKES_ORDER|| cakes.size() > PRIVATE_MAX_NUMBER_CAKES_ORDER) {
            return false;
        }
        return true;
    }

    @Override
    public double getDiscount(double totalPrice) {
        double discount = 0;
        for (Integer amount : this.vauchers) {
            discount += amount;
        }
        totalPrice -= discount;
        return totalPrice;
    }

}
