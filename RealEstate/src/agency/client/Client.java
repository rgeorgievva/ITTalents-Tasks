package agency.client;

import agency.Agency;
import agency.Agent;

public abstract class Client {

    public static Agency agency;
    private String name;
    private String mobileNumber;
    private double money;
    private Agent agent;

    public Client(String name, String mobileNumber, double money) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.money = money;
        this.agent = null;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    protected double getMoney() {
        return this.money;
    }

    public Agent getAgent() {
        return agent;
    }

    public void spendMoney(double amount) {
        this.money -= amount;
    }
}
