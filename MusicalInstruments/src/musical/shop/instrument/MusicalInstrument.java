package musical.shop.instrument;

public abstract class MusicalInstrument {

    private String name;
    private double price;
    private InstrumentKind instrumentKind;

    public MusicalInstrument(String name, double price, InstrumentKind instrumentKind) {
        this.name = name;
        this.price = price;
        this.instrumentKind = instrumentKind;
    }

    public String getName() {
        return name;
    }

    public InstrumentKind getInstrumentKind() {
        return instrumentKind;
    }

    public double getPrice() {
        return price;
    }
}
