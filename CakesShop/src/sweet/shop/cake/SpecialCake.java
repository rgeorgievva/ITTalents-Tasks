package sweet.shop.cake;

import sweet.shop.cake.kind.Special;

public class SpecialCake extends Cake implements Special, Comparable {

    @Override
    public String getKind() {
        return "Special";
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (o instanceof SpecialCake) {
            SpecialCake cake = (SpecialCake) o;
            if (this.getPrice() == cake.getPrice()) {
                return 1;
            }
            return Double.compare(cake.getPrice(), this.getPrice());
        }
        return 1;
    }

    public enum SpecialCakeType implements CakeType  {

        ANNIVERSARY, CORPORATE, ADVERTISING;
    }

    private String eventName;

    public SpecialCake(String name, String description, double price, int numberPieces,
                       SpecialCakeType type, String eventName) {
        super(name, description, price, numberPieces, type);
        this.eventName = eventName;
    }

}
