package sweet.shop.client;

import sweet.shop.Order;
import sweet.shop.Supplier;
import sweet.shop.cake.Cake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class CorporateClient extends Client {

    private static final int CORPORATE_MIN_NUMBER_CAKES_ORDER = 3;
    private static final int CORPORATE_MAX_NUMBER_CAKES_ORDER = 5;
    private static final int MIN_DISCOUNT_PERCENTAGE = 5;
    private static final int MAX_DISCOUNT_PERCENTAGE = 15;
    private static final double CORPORATE_TIP_PERCENTAGE = 0.05;

    public CorporateClient(String name, String mobileNumber) {
        super(name, mobileNumber);
        this.tipPercentage = CORPORATE_TIP_PERCENTAGE;
    }

    @Override
    public boolean isValidCakesNumberOrder(ArrayList<Cake> cakes) {
        if (cakes.size() < CORPORATE_MIN_NUMBER_CAKES_ORDER || cakes.size() > CORPORATE_MAX_NUMBER_CAKES_ORDER) {
            return false;
        }
        return true;
    }

    @Override
    public double getDiscount(double totalPrice) {
        double discountPercentage = (double)(new Random().nextInt(10) + 5) / 100;
        totalPrice -= totalPrice * discountPercentage;
        return totalPrice;
    }


}
