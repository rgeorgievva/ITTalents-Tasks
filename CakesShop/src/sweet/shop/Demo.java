package sweet.shop;

import sweet.shop.cake.*;
import sweet.shop.client.Client;
import sweet.shop.client.CorporateClient;
import sweet.shop.client.PrivateClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Demo {

    public static void main(String[] args) {

        String[] names = {"Georgi", "Petur", "Ivan", "Yordan", "Maria", "Viktoriya"};
        ArrayList<Supplier> suppliers = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int nameIdx = r.nextInt(names.length);
            Supplier supplier = new Supplier(names[nameIdx], "123-456");
            suppliers.add(supplier);

        }

        SweetShop sweetShop = new SweetShop("SweetTalents", "Sofia",
                "222-555-777", suppliers);

        Supplier.sweetShop = sweetShop;

        ArrayList<Cake> cakes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int cakeKindChance = r.nextInt(4);
            int cakeTypeChance;
            CakeType type = null;
            Cake cake = null;
            int numberPieces = r.nextInt(25) + 15;
            int price = r.nextInt(50) + 25;
            switch (cakeKindChance) {
                case 0:
                    cakeTypeChance = r.nextInt(4);
                    boolean isSyrupy = r.nextBoolean();
                    switch (cakeTypeChance) {
                        case 0:
                            type = StandardCake.StandardCakeType.BISCUIT;
                            break;
                        case 1:
                            type = StandardCake.StandardCakeType.CHOCOLATE;
                            break;
                        case 2:
                            type = StandardCake.StandardCakeType.ECLAIR;
                            break;
                        case 3:
                            type = StandardCake.StandardCakeType.FRUIT;
                            break;
                    }
                    cake = new StandardCake("standard", "standard", price, numberPieces,
                            (StandardCake.StandardCakeType) type, isSyrupy);
                    break;
                case 1 :
                    cakeTypeChance = r.nextInt(3);
                    switch (cakeTypeChance) {
                        case 0:
                            type = ChildrenCake.ChildrenCakeType.BAPTISM;
                            break;
                        case 1:
                            type = ChildrenCake.ChildrenCakeType.BIRTHDAY;
                            break;
                        case 2:
                            type = ChildrenCake.ChildrenCakeType.PURVEYOR;
                            break;
                    }
                    cake = new ChildrenCake("children", "children", price, numberPieces,
                            (ChildrenCake.ChildrenCakeType) type, names[r.nextInt(names.length)]);
                    break;
                case 2:
                    cakeTypeChance = r.nextInt(3);
                    switch (cakeTypeChance) {
                        case 0:
                            type = WeddingCake.WeddingCakeType.AVERAGE;
                            break;
                        case 1:
                            type = WeddingCake.WeddingCakeType.BIG;
                            break;
                        case 2:
                            type = WeddingCake.WeddingCakeType.SMALL;
                            break;
                    }
                    cake = new WeddingCake("wedding", "wedding", price,numberPieces,
                            (WeddingCake.WeddingCakeType) type, (r.nextInt(3) + 1));
                    break;
                case 3:
                    cakeTypeChance = r.nextInt(3);
                    switch (cakeTypeChance) {
                        case 0:
                            type = SpecialCake.SpecialCakeType.ADVERTISING;
                            break;
                        case 1:
                            type = SpecialCake.SpecialCakeType.ANNIVERSARY;
                            break;
                        case 2:
                            type = SpecialCake.SpecialCakeType.CORPORATE;
                            break;
                    }
                    cake = new SpecialCake("special", "special", price, numberPieces,
                            (SpecialCake.SpecialCakeType) type, "event");
                    break;
            }
            sweetShop.addCake(cake);
            cakes.add(cake);
        }

        Client.sweetShop = sweetShop;

        System.out.println("====Cakes Catalogue====");
        sweetShop.printAvailableCakes();

        for (int i = 0; i < 5; i++) {
            Client corporate = new CorporateClient(names[r.nextInt(names.length)], "777-888");
            ArrayList<Cake> order = new ArrayList<>();
            int numberCakes = r.nextInt(2) + 3;
            for (int j = 0; j < numberCakes; j++) {
                order.add(cakes.get(r.nextInt(cakes.size())));
            }
            corporate.orderCake(order, "bul. Bulgaria");
        }
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> vauchers = new ArrayList<>();
            int numberVauchers = r.nextInt(4) + 1;
            for (int j = 0; j < numberVauchers; j++) {
                vauchers.add(r.nextInt(20) + 10);
            }
            Client privateClient = new PrivateClient(names[r.nextInt(names.length)], "999-888", vauchers);
            int numCakes = r.nextInt(2) + 1;
            ArrayList<Cake> order = new ArrayList<>();
            for (int j = 0; j < numCakes; j++) {
                order.add(cakes.get(r.nextInt(cakes.size())));
            }
            privateClient.orderCake(order, "Studentski grad");
        }

        System.out.println("====Cakes Catalogue====");
        sweetShop.printAvailableCakes();

        System.out.println("\nSweet Shop balance: " + sweetShop.getMoney());

        sweetShop.printSuppliersInfoSortedByTips();

        sweetShop.printMostSoldKindCake();

        sweetShop.printSupplierWithMostOrders();

        sweetShop.printClientWhoSpendMostMoney();

    }
}
