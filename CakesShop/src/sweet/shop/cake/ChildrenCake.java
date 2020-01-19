package sweet.shop.cake;

import sweet.shop.cake.kind.Children;

import java.util.Objects;

public class ChildrenCake extends Cake implements Children, Comparable {

    public enum ChildrenCakeType implements CakeType {

        BIRTHDAY, BAPTISM, PURVEYOR;

    }
    private String childName;

    public ChildrenCake(String name, String description, double price,
                        int numberPieces, ChildrenCakeType type, String childName) {
        super(name, description, price, numberPieces, type);
        this.childName = childName;
    }

    @Override
    public String getKind() {
        return "Children";
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (o instanceof ChildrenCake) {
            ChildrenCake cake = (ChildrenCake) o;
            if (this.getNumberPieces() == cake.getNumberPieces()) {
                return 1;
            }
            return cake.getNumberPieces() - this.getNumberPieces();
        }
        return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(childName);
    }

}
