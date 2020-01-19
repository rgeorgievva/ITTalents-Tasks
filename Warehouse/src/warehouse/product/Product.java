package warehouse.product;

public abstract class Product {

    public enum Type {

        FRUIT, VEGETABLE, MEAT
    }

    public interface Name {

    }

    private Name name;
    private Type type;

    Product(Name name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public Name getName() {
        return this.name;
    }
}
