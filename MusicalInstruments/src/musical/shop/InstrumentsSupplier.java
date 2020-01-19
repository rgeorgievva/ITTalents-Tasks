package musical.shop;

import musical.shop.instrument.MusicalInstrument;

import java.util.HashMap;

public class InstrumentsSupplier extends Thread {

    public static Shop shop;

    private HashMap<String, Integer> availableInstruments;

    public InstrumentsSupplier() {
        this.availableInstruments = new HashMap<>();
        this.availableInstruments.put("Drums", 3);
        this.availableInstruments.put("Piano", 7);
        this.availableInstruments.put("Viola", 2);
        this.availableInstruments.put("Bas Guitar", 4);
        this.availableInstruments.put("Flute", 1);
        this.availableInstruments.put("Guitar", 5);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
            shop.deliverInstruments();
        }
    }

    public void deliverInstrument(MusicalInstrument instrument, Shop shop) {
        try {
            Thread.sleep(getTimeForInstrumentDelivery(instrument) * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while delivering instrument.");
        }
        shop.acceptInstrumentsForSale(instrument, 1);
        System.out.println("The instrument was delivered!");
    }

        public int getTimeForInstrumentDelivery(MusicalInstrument instrument) {
        return availableInstruments.get(instrument.getName());
    }
}
