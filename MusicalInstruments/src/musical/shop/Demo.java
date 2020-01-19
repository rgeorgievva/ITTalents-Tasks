package musical.shop;

import musical.shop.instrument.*;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

        Shop shop = new Shop();
        InstrumentsSupplier instrumentsSupplier = new InstrumentsSupplier();
        Shop.instrumentsSupplier = instrumentsSupplier;
        InstrumentsSupplier.shop = shop;

        instrumentsSupplier.start();

        Guitar guitar = new Guitar(60);
        Drums drums = new Drums(250);
        Viola viola = new Viola(120);
        Piano piano = new Piano(500);
        BasGuitar basGuitar = new BasGuitar(200);
        Flute flute = new Flute(170);

        shop.acceptInstrumentsForSale(guitar, 3);
        shop.acceptInstrumentsForSale(viola, 2);
        shop.acceptInstrumentsForSale(drums, 7);
        shop.acceptInstrumentsForSale(piano, 5);
        shop.acceptInstrumentsForSale(basGuitar, 10);
        shop.acceptInstrumentsForSale(flute, 17);

        shop.catalogueAvailableByKind();
        shop.catalogueAvailableByName();

        shop.sellInstrument(drums, 3);
        shop.sellInstrument(piano, 4);
        shop.sellInstrument(viola, 1);

        Scanner sc = new Scanner(System.in);
        System.out.println("=====Catalogue Available By Price=====");
        System.out.println("Press D for descending order or A for ascending:");
        char order = sc.next().charAt(0);
        switch (order) {
            case 'A':
            case 'a':
                shop.catalogueAvailableByPriceAscending();
                break;
            case 'D':
            case 'd':
                shop.catalogueAvailableByPriceDescending();
                break;
            default:
                System.out.println("INCORRECT SYMBOL!");
                break;
        }

        System.out.println("\nTotal profit: " + shop.getTotalProfit() + "\n");

        shop.catalogueSoldByNumberOfSales();
        shop.printMostSoldInstrumentInfo();
        shop.printLeastSoldInstrumentInfo();
        shop.printMostSoldKindOfInstrument();

        shop.sellInstrument(flute, 17);

        shop.printMostSoldKindOfInstrument();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("oops");
        }
        shop.catalogueAvailableByName();
    }
}
