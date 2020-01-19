import agency.Agency;
import agency.Agent;
import agency.client.Buyer;
import agency.client.Client;
import agency.client.Seller;
import agency.real.estate.*;

import java.util.ArrayList;
import java.util.Random;

public class Demo {

    public static void main(String[] args) {

        final String[] names = {"Ivan", "Yordan", "Petyr", "Georgi", "Simeon", "Desislava", "Viktoriya", "Rayna", "Katya"};

        ArrayList<Agent> agents = new ArrayList<>();

        int numberAgents = 5;

        Random r = new Random();
        for (int i = 0; i < numberAgents; i++) {
            Agent agent = new Agent(names[r.nextInt(names.length)], "777-888-999");
            agents.add(agent);
        }

        Agency agency = new Agency("Talanti Estates", "Sofia, bul.Bulgaria", "123-456", agents);

        Client.agency = agency;

        int numberSellers = 30;
        ArrayList<RealEstate> realEstates = new ArrayList<>();
        ArrayList<Seller> sellers = new ArrayList<>();
        for (int i = 0; i < numberSellers; i++) {
            RealEstate realEstate = null;
            int realEstateTypeChance = r.nextInt(3);

            switch (realEstateTypeChance) {
                case 0:
                    realEstate = new Apartment("apartment", "bul. Cherni vruh",
                            r.nextInt(80000) + 70000, r.nextInt(70) + 30,
                            getRandomApartmentKind(),getRandomConstructionType());
                    break;
                case 1:
                    realEstate = new House("house", "Boyana", r.nextInt(30000) + 50000,
                            r.nextInt(150) + 100, getRandomHouseKind(), getRandomConstructionType());
                    break;
                case 2:
                    realEstate = new Parcel("parcel", "Vladaya",r.nextInt(55000) + 30000,
                            r.nextInt(3000) + 100, getRandomParcelKind(), r.nextBoolean());
                    break;
            }
            Seller seller = new Seller(names[r.nextInt(names.length)], "333-777",
                    r.nextInt(5000) + 5000, realEstate);
            seller.sellRealEstate();
            sellers.add(seller);
            realEstates.add(realEstate);
        }

        int numberBuyers = 10;
        for (int i = 0; i < numberBuyers; i++) {
            Buyer buyer = new Buyer(names[r.nextInt(names.length)], "6767",
                    r.nextInt(120000) + 30000);
            buyer.searchRealEstate();
            RealEstate realEstate = null;
            for (int j = 0; j < 3; j++) {
                realEstate = realEstates.get(r.nextInt(realEstates.size()));
                buyer.stateView(realEstate);
            }
            buyer.buyRealEstate(realEstate);
        }

        System.out.println("===AGENCY MONEY==");
        System.out.println(agency.getMoney());

        agency.printAgentsByMoney();


    }

    public static Apartment.ApartmentKind getRandomApartmentKind() {
        Random r = new Random();
        int apartmentKindChance = r.nextInt(5);
        switch (apartmentKindChance) {
            case 0:
                return Apartment.ApartmentKind.BED_SITTER;
            case 1:
                return Apartment.ApartmentKind.MAISONETTE;
            case 2:
                return Apartment.ApartmentKind.ONE_BEDROOM;
            case 3:
                return Apartment.ApartmentKind.TWO_BEDROOM;
            case 4:
                return Apartment.ApartmentKind.STUDIO;
            default:
                return null;
        }
    }

    public static House.HouseKind getRandomHouseKind() {
        Random r = new Random();
        int houseKindChance = r.nextInt(5);
        switch (houseKindChance) {
            case 0:
                return House.HouseKind.HOUSE_FLOOR;
            case 1:
                return House.HouseKind.WHOLE_HOUSE;
            default:
                return null;
        }
    }

    public static Parcel.ParcelKind getRandomParcelKind() {
        Random r = new Random();
        int parcelKindChance = r.nextInt(5);
        switch (parcelKindChance) {
            case 0:
                return Parcel.ParcelKind.FIELD;
            case 1:
                return Parcel.ParcelKind.FOREST;
            case 2:
                return Parcel.ParcelKind.MEADOW;
            default:
                return null;
        }
    }

    public static ConstructionType getRandomConstructionType() {
        Random r = new Random();
        int constructionTypeChance = r.nextInt(4);

        switch (constructionTypeChance) {
            case 0:
                return ConstructionType.EPC;
            case 1:
                return ConstructionType.BRICK;
            case 2:
                return ConstructionType.COB;
            case 3:
                return ConstructionType.PANEL;
            default:
                return null;
        }
    }
}
