package agency.client;

import agency.Agency;
import agency.Agent;
import agency.real.estate.RealEstate;

public class Seller extends Client {

    private RealEstate realEstate;

    public Seller(String name, String mobileNumber, double money, RealEstate realEstate) {
        super(name, mobileNumber, money);
        this.realEstate = realEstate;
        this.realEstate.setSeller(this);
    }

    public void sellRealEstate() {
        Client.agency.addRealEstateToCatalogue(this.realEstate);
        Agent agent = Client.agency.getRandomAgent();
        realEstate.setAgent(agent);
        agent.addSeller(this);
        this.setAgent(agent);
    }
}
