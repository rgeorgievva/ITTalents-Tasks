package warehouse.product;

public class Vegetable extends Product {

    public enum Name implements Product.Name {

        PATATO, EGGPLANT, CUCUMBER
    }

    public Vegetable(Name name) {
        super(name, Type.VEGETABLE);
    }
}
