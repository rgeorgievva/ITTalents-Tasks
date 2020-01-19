package sweet.shop.cake;

import sweet.shop.cake.kind.Wedding;

public class WeddingCake extends Cake implements Wedding, Comparable {

    @Override
    public String getKind() {
        return "Wedding";
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (o instanceof WeddingCake) {
            WeddingCake cake = (WeddingCake) o;
            if (this.getNumberPieces() == cake.getNumberPieces()) {
                return 1;
            }
            return cake.getNumberPieces() - this.getNumberPieces();
        }
        return 1;
    }

    public enum WeddingCakeType implements CakeType {

        BIG, SMALL, AVERAGE;
    }

    private int numberFloors;

    public WeddingCake(String name, String description, double price, int numberPieces,
                       WeddingCakeType type, int numberFloors) {
        super(name, description, price, numberPieces, type);
        this.numberFloors = numberFloors;
    }

}
