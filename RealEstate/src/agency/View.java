package agency;

import agency.client.Buyer;
import agency.real.estate.RealEstate;

import java.time.LocalDate;

public class View {

    private RealEstate realEstate;
    private Agent agent;
    private Buyer buyer;
    private LocalDate date;

    public View(RealEstate realEstate, Agent agent, Buyer buyer, LocalDate date) {
        this.realEstate = realEstate;
        this.agent = agent;
        this.buyer = buyer;
        this.date = date;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }
}
