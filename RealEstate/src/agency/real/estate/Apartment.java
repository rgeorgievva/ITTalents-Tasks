package agency.real.estate;

public class Apartment extends RealEstate {

    public enum ApartmentKind implements Kind {

        STUDIO, BED_SITTER, ONE_BEDROOM, TWO_BEDROOM, MAISONETTE
    }


    private ConstructionType constructionType;

    public Apartment(String description, String address, double price, double area,
                      ApartmentKind kind, ConstructionType constructionType) {
        super(description, address, price, area, Type.APARTMENT, kind);
        this.constructionType = constructionType;
    }
}
