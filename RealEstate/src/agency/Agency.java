package agency;

import agency.real.estate.RealEstate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

public class Agency {

    private String name;
    private String address;
    private String mobileNumber;
    private ArrayList<Agent> agents;
    private HashMap<RealEstate.Type, TreeSet<RealEstate>> adsCatalogue;
    private double money;

    public Agency(String name, String address, String mobileNumber, ArrayList<Agent> agents) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.agents = agents;
        this.adsCatalogue = new HashMap<>();
        this.money = 0.0;
    }

    public void addRealEstateToCatalogue(RealEstate realEstate) {

        if (!adsCatalogue.containsKey(realEstate.getType())) {
            adsCatalogue.put(realEstate.getType(), new TreeSet<>((r1, r2) -> {
                if (r1.getPrice() == r2.getPrice()) {
                    return 1;
                }
                return Double.compare(r2.getPrice(), r1.getPrice());
            }));
        }

        adsCatalogue.get(realEstate.getType()).add(realEstate);
    }

    public Agent getRandomAgent() {
        Random r = new Random();
        int numberAgent = r.nextInt(agents.size());

        return this.agents.get(numberAgent);
    }

    public void receiveMoney(double commission, Agent agent) {
        if (commission > 0) {
            agent.receiveMoney(commission / 2);
            this.money += commission / 2;
        }
    }

    public double getMoney() {
        return this.money;
    }

    public void printAgentsByMoney() {
        TreeSet<Agent> agentsByMoney = new TreeSet<>((a1, a2) -> {
            if (a1.getMoney() == a2.getMoney()) {
                return 1;
            }
            return Double.compare(a2.getMoney(), a1.getMoney());
        });

        agentsByMoney.addAll(agents);

        for (Agent agent : agentsByMoney) {
            System.out.println(agent.getName() + " - " + agent.getMoney());
        }
    }
}
