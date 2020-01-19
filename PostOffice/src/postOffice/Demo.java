package postOffice;

import postOffice.people.Citizen;

public class Demo {

    public static final int NUMBER_CITIZENS = 15;

    public static void main(String[] args) {

        PostOffice.getInstance();

        for (int i = 0; i < NUMBER_CITIZENS; i++) {
            Citizen citizen = new Citizen("Citizen" + (i + 1), "Petrov", "Sofia");
            new Thread(citizen).start();
        }

    }
}
