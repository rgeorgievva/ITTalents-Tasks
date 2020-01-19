package musical.shop.instrument;

public class Guitar extends MusicalInstrument {

    public Guitar(double price) {
        super("Guitar", price, InstrumentKind.STRINGS);
    }
}
