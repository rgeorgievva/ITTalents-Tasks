package coloring.people;

import coloring.Egg;
import coloring.Refrigerator;

import java.util.Random;

public class Mother extends Person {

    private static final int WRAP_IN_COTTON_CHANCE_PERCENTAGE = 20;
    private static final int TOTAL_NUMBER_EGGS = 50;

    public static Refrigerator refrigerator;

    public Mother(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            if (refrigerator.getTotalEggs() == TOTAL_NUMBER_EGGS) {
                return;
            }
            Egg egg = table.removeEgg();
            System.out.println(this.getName() + " starts to dry " + egg.getType() + " egg.");
            dryEgg(egg);
            refrigerator.putEgg(egg);
        }
    }

    private void dryEgg(Egg egg) {
        try {
            int wrapInCottonChance = new Random().nextInt(100);
            if (wrapInCottonChance <= 20) {
                System.out.println(this.getName() + " wrapped the egg in cotton.");
                Thread.sleep(3000);//5 seconds in cotton
                egg.setColourful();
            }
            else {
                Thread.sleep(1500);//2 seconds drying
            }
        } catch (InterruptedException e) {
            System.out.println("Mother was interrupted while drying an egg.");
        }
    }
}
