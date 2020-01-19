package warehouse.product;

public class Meat extends Product {

    public enum Name implements Product.Name {

        PORK, BEEF, CHICKEN
    }

    public Meat(Name name) {
        super(name, Type.MEAT);
    }
}
