package agency.client;

import agency.Agent;
import agency.View;
import agency.real.estate.RealEstate;

import java.time.LocalDate;
import java.util.HashSet;

public class Buyer extends Client {

    private static final double COMMISSION_PERCENTAGE = 0.03;

    private HashSet<View> views;

    public Buyer(String name, String mobileNumber, double money) {
        super(name, mobileNumber, money);
        this.views = new HashSet<>();
    }

    public void addView(View view) {
        this.views.add(view);
    }

    public void searchRealEstate() {
        Agent agent = Client.agency.getRandomAgent();
        this.setAgent(agent);
        agent.addBuyer(this);
    }

    public void stateView(RealEstate realEstate) {
        if (realEstate == null) {
            return;
        }

        if (realEstate.getPrice() > this.getMoney()) {
            System.out.println("Cannot view " + realEstate + " because you don't have that much money");
            return;
        }

        View view = new View(realEstate, this.getAgent(), this, LocalDate.now());
        this.addView(view);
        getAgent().addView(view);
    }

    public void buyRealEstate(RealEstate realEstate) {
        if (!this.hasViewed(realEstate)) {
            System.out.println("You should first view the real estate before buying it!");
            return;
        }

        double price = realEstate.getPrice();
        double commission = price * COMMISSION_PERCENTAGE;

        //client gives agency commission
        Client.agency.receiveMoney(commission, this.getAgent());
        this.spendMoney(commission);

        //seller gives agency commission
        Seller seller = realEstate.getSeller();
        Client.agency.receiveMoney(commission, this.getAgent());
        seller.spendMoney(commission);
    }


    private boolean hasViewed(RealEstate realEstate) {
        for (View view : this.views) {
            if (view.getRealEstate().equals(realEstate)) {
                return true;
            }
        }

        return false;
    }
}
