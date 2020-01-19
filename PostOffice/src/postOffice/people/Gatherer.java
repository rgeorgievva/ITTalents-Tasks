package postOffice.people;

import postOffice.PostOffice;

public class Gatherer extends Mailman {

    public Gatherer(String firstName, String familyName, int experience) {
        super(firstName, familyName, experience);
    }

    private void gatherLetters() {
        PostOffice.getInstance().sendGatherersToPickLetters();
    }

    @Override
    public void run() {
        while (true) {
            gatherLetters();
        }
    }
}
