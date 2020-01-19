package postOffice.people;

import postOffice.PostOffice;

public class Mailman extends Human implements Runnable {

    private int experience;
    private boolean busy;

    public Mailman(String firstName, String familyName, int experience) {
        super(firstName, familyName);
        this.experience = experience;
    }

    @Override
    public void run() {
        while (true) {
            PostOffice.getInstance().deliverPackets(this);
        }
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
