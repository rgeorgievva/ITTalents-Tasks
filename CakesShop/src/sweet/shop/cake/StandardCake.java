package sweet.shop.cake;

import sweet.shop.cake.kind.Standard;

public class StandardCake extends Cake implements Standard, Comparable {

    @Override
    public String getKind() {
        return "Standard";
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (o instanceof StandardCake) {
            StandardCake cake = (StandardCake) o;
            if (this.getPrice() == cake.getPrice()) {
                return 1;
            }
            return Double.compare(cake.getPrice(), this.getPrice());
        }
        return 1;
    }

    public enum StandardCakeType implements CakeType  {

        BISCUIT, ECLAIR, FRUIT, CHOCOLATE;
    }

    private boolean isSyrupy;

    public StandardCake(String name, String description, double price, int numberPieces,
                        StandardCakeType type, boolean isSyrupy) {
        super(name, description, price, numberPieces, type);
        this.isSyrupy = isSyrupy;
    }

}
