package musical.shop;

import musical.shop.instrument.Flute;
import musical.shop.instrument.InstrumentKind;
import musical.shop.instrument.MusicalInstrument;

import java.util.*;

public class Shop {

    public static InstrumentsSupplier instrumentsSupplier;

    private double money;
    private HashMap<InstrumentKind, TreeMap<String, ArrayList<MusicalInstrument>>> availableInstruments;
    private HashMap<InstrumentKind, TreeMap<String, ArrayList<MusicalInstrument>>> soldInstruments;
    private int numberSoldInstruments;

    public Shop() {
        this.availableInstruments = new HashMap<>();
        this.soldInstruments = new HashMap<>();
        this.numberSoldInstruments = 0;
    }

    private void addInstrumentToCatalogue(HashMap<InstrumentKind, TreeMap<String, ArrayList<MusicalInstrument>>> catalogue,
                                          MusicalInstrument instrument, int number) {
        if (!catalogue.containsKey(instrument.getInstrumentKind())) {
            catalogue.put(instrument.getInstrumentKind(), new TreeMap<>());
        }

        if (!catalogue.get(instrument.getInstrumentKind()).containsKey(instrument.getName())) {
            catalogue.get(instrument.getInstrumentKind()).put(instrument.getName(), new ArrayList<>());
        }

        for (int i = 0; i < number; i++) {
            catalogue.get(instrument.getInstrumentKind()).get(instrument.getName()).add(instrument);
        }
    }

    private boolean isAvailable(MusicalInstrument instrument) {
        if (!this.availableInstruments.containsKey(instrument.getInstrumentKind())) {
            return false;
        }

        if (!this.availableInstruments.get(instrument.getInstrumentKind()).containsKey(instrument.getName())) {
            return false;
        }

        return this.availableInstruments.get(instrument.getInstrumentKind()).get(instrument.getName()).size() != 0;
    }

    public void sellInstrument(MusicalInstrument instrument, int number) {
        if (!isAvailable(instrument)) {
            System.out.println("We don't have " + instrument.getName() + " available!");
            deliverInstrumentForClient(instrument);
            return;
        }

        int numberAvailable = this.availableInstruments.get(instrument.getInstrumentKind())
                .get(instrument.getName()).size();

        if (number > numberAvailable) {
            System.out.println("We don't have " + number + " " + instrument.getName() +
                    "s available, we only have " + numberAvailable + " numbers left.");
            return;
        }

        //accept money for the sold instruments
        double totalPrice = number * instrument.getPrice();
        this.money += totalPrice;

        //remove the sold instruments from the catalogue with available instruments
        for (int i = 0; i < number; i++) {
            this.availableInstruments.get(instrument.getInstrumentKind()).get(instrument.getName()).remove(instrument);
        }

        //add the sold instruments to the catalogue with sold instruments
        addInstrumentToCatalogue(soldInstruments, instrument, number);
        this.numberSoldInstruments+= number;

    }

    private TreeMap<Integer, String> getSoldInstrumentsByNumberOfSales() {
        TreeMap<Integer, String> soldByNumberOfSales = new TreeMap<>((n1, n2) -> {
            if (n1 == n2) {
                return 1;
            }

            return n1.compareTo(n2);
        });

        for (InstrumentKind kind : soldInstruments.keySet()) {
            for (Map.Entry<String, ArrayList<MusicalInstrument>> entry : soldInstruments.get(kind).entrySet()) {
                String name = entry.getKey();
                Integer numberSales = entry.getValue().size();
                if (!soldByNumberOfSales.containsKey(numberSales)) {
                    soldByNumberOfSales.put(numberSales, name);
                }
            }
        }

        return soldByNumberOfSales;
    }

    public void catalogueSoldByNumberOfSales() {
        TreeMap<Integer, String> soldByNumberOfSales = getSoldInstrumentsByNumberOfSales();

        System.out.println("Sold instruments sorted by number of sales:");
        for (Map.Entry<Integer, String> entry : soldByNumberOfSales.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
    }

    public void printMostSoldInstrumentInfo() {
        TreeMap<Integer, String> soldByNumberOfSales = getSoldInstrumentsByNumberOfSales();

        String mostSoldName = soldByNumberOfSales.lastEntry().getValue();
        Integer mostSoldNumberSales = soldByNumberOfSales.lastKey();

        System.out.println("===MOST SOLD INSTRUMENT INFORMATION===");
        System.out.println(mostSoldName + " - " + mostSoldNumberSales + " sales of total " +
                numberSoldInstruments + " number of sales");
    }

    public void printLeastSoldInstrumentInfo() {
        TreeMap<Integer, String> soldByNumberOfSales = getSoldInstrumentsByNumberOfSales();

        String leastSoldName = soldByNumberOfSales.firstEntry().getValue();
        Integer leastSoldNumberSales = soldByNumberOfSales.firstKey();

        System.out.println("===LEAST SOLD INSTRUMENT INFORMATION===");
        System.out.println(leastSoldName + " - " + leastSoldNumberSales + " sales of total " +
                numberSoldInstruments + " number of sales");
    }

    public void printMostSoldKindOfInstrument() {
        int maxSoldNumber = 0;
        InstrumentKind mostSoldKind = null;

        for (InstrumentKind kind : this.soldInstruments.keySet()){
            int currentKindNumber = 0;
            for (ArrayList<MusicalInstrument> instruments : this.soldInstruments.get(kind).values()) {
                currentKindNumber += instruments.size();
            }
            if (currentKindNumber > maxSoldNumber) {
                maxSoldNumber = currentKindNumber;
                mostSoldKind = kind;
            }
        }

        System.out.println("\nMost sold kind of instruments: " + mostSoldKind + " - " + maxSoldNumber + " sales");
    }

    public void acceptInstrumentsForSale(MusicalInstrument instrument, int number) {
        addInstrumentToCatalogue(availableInstruments, instrument, number);
    }

    public void catalogueAvailableByKind() {
        for (InstrumentKind kind : availableInstruments.keySet()) {
            System.out.println("=====" + kind + "=====");
            for (String name : availableInstruments.get(kind).keySet()) {
                System.out.println(name + " - " + availableInstruments.get(kind).get(name).size());
            }
        }
    }

    public void catalogueAvailableByName() {
        System.out.println("=====Catalogue Available By Name=====");
        TreeMap<String, ArrayList<MusicalInstrument>> availableByName = new TreeMap<>();

        for (InstrumentKind kind : this.availableInstruments.keySet()) {
            availableByName.putAll(availableInstruments.get(kind));
        }

        for (String name : availableByName.keySet()) {
            System.out.println(name + " - " + availableByName.get(name).size());
        }
    }

    private void addInstrumentsToTreeMap(Comparator<Double> comparator) {
        TreeMap<Double, ArrayList<MusicalInstrument>> instuments = new TreeMap<>(comparator);

        for (InstrumentKind kind : availableInstruments.keySet()) {
            for (String name : availableInstruments.get(kind).keySet()) {
                MusicalInstrument instrument = availableInstruments.get(kind).get(name).get(0);
                if (!instuments.containsKey(instrument.getPrice())) {
                    instuments.put(instrument.getPrice(), new ArrayList<>());
                }
                for (int i = 0; i < availableInstruments.get(kind).get(name).size(); i++) {
                    instuments.get(instrument.getPrice()).add(instrument);
                }
            }
        }

        for (Map.Entry<Double, ArrayList<MusicalInstrument>> entry : instuments.entrySet()) {
            System.out.println(entry.getValue().get(0).getName() + " - " + entry.getValue().size() +
                    " , price: " + entry.getKey());
        }
    }

    public double getTotalProfit() {
        return this.money;
    }

    public void catalogueAvailableByPriceAscending() {
        Comparator<Double> instrumentsByPriceAscending = ((p1, p2) -> {
            if (p1 == p2) {
                return 1;
            }
            return Double.compare(p1, p2);
        });

        addInstrumentsToTreeMap(instrumentsByPriceAscending);
    }

    public void catalogueAvailableByPriceDescending() {
        Comparator<Double> instrumentsByPriceDescending = ((p1, p2) -> {
            if (p1 == p2) {
                return 1;
            }
            return Double.compare(p2, p1);
        });

        addInstrumentsToTreeMap(instrumentsByPriceDescending);
    }

    private void deliverInstrumentForClient(MusicalInstrument instrument) {
        int timeForDelivery = instrumentsSupplier.getTimeForInstrumentDelivery(instrument);
        System.out.println("The delivery of " + instrument.getName() + " will take " + timeForDelivery + " seconds." );
        System.out.println("Do you want to order?(Press Y for Yes or N for No)");
        Scanner sc = new Scanner(System.in);
        char symbol = sc.next().charAt(0);
        switch (symbol) {
            case 'n':
            case 'N':
                return;
            case 'y':
            case 'Y':
                instrumentsSupplier.deliverInstrument(instrument, this);
                break;
        }
    }

    public void deliverInstruments() {
        ArrayList<MusicalInstrument> instrumentsWithNoAvailability = getInstrumentsWithNoAvailability();

        for (MusicalInstrument instrument : instrumentsWithNoAvailability) {
            this.acceptInstrumentsForSale(instrument, 5);
        }
    }

    private ArrayList<MusicalInstrument> getInstrumentsWithNoAvailability() {
        ArrayList<MusicalInstrument> instrumentsWithNoAvailability = new ArrayList<>();
        for (InstrumentKind kind : availableInstruments.keySet()) {
            for (String name : availableInstruments.get(kind).keySet()) {
                if (availableInstruments.get(kind).get(name).size() == 0) {
                    MusicalInstrument instrument = null;
                    switch (name) {
                        case "Flute":
                            instrument = new Flute(30);
                            break;
                    }
                    instrumentsWithNoAvailability.add(instrument);
                }
            }
        }

        return instrumentsWithNoAvailability;
    }
}
