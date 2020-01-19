package rakidjiinica;

import rakidjiinica.human.BrandyMaker;
import rakidjiinica.human.Human;
import rakidjiinica.human.Picker;

import java.util.Random;

public class Demo {

    public static final int NUMBER_PICKERS = 7;

    public static void main(String[] args) {

        Rakidjiinica rakidjiinica = new Rakidjiinica();
        Human.rakidjiinica = rakidjiinica;

        for (int i = 0; i < NUMBER_PICKERS; i++) {
            String name = "PICKER " + (i + 1);
            int age = new Random().nextInt(30) + 20;
            Picker picker = new Picker(name, age, getFruit());
            picker.start();
        }

        BrandyMaker ivan = new BrandyMaker("Ivan" , 53);
        BrandyMaker georgi = new BrandyMaker("Georgi", 60);
        BrandyMaker petyr = new BrandyMaker("Petyr", 73);

        ivan.start();
        georgi.start();
        petyr.start();

        Statistician statistician = new Statistician(rakidjiinica);
        statistician.setDaemon(true);
        statistician.start();
    }

    public static Fruit getFruit() {
        int fruitChance = new Random().nextInt(3);
        switch (fruitChance) {
            case 0:
                return Fruit.GRAPES;
            case 1:
                return Fruit.PLUMS;
            case 2:
                return Fruit.APRICOT;
        }

        return null;
    }
}
