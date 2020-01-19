package sweet.shop.cake;

public abstract class Cake {

    private String name;
    private String description;
    private double price;
    private int numberPieces;
    protected CakeType type;

    public Cake(String name, String description, double price, int numberPieces, CakeType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberPieces = numberPieces;
        this.type = type;
    }

    public abstract String getKind();

    public CakeType getType() {
        return this.type;
    }

    public double getPrice() {
        return price;
    }

    protected int getNumberPieces() {
        return numberPieces;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", numberPieces=" + numberPieces +
                ", kind=" + getKind() +
                ", type=" + type +
                '}';
    }
}
