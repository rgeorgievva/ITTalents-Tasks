package warehouse.product;

public class Fruit extends Product {

    public enum Name implements Product.Name {

        BANANA, ORANGE, APPLE
    }

    public Fruit(Name name) {
        super(name, Type.FRUIT);
    }
}
