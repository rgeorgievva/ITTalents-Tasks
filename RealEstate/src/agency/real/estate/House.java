package agency.real.estate;

public class House extends RealEstate {

    public enum HouseKind implements Kind {

        HOUSE_FLOOR, WHOLE_HOUSE
    }


    private ConstructionType constructionType;

    public House(String description, String address, double price, double area,
                HouseKind kind, ConstructionType constructionType) {
        super(description, address, price, area, Type.HOUSE, kind);
        this.constructionType = constructionType;
    }

}
