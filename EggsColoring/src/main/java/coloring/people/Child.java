package coloring.people;

import coloring.Egg;
import coloring.Pot;

public class Child extends Person {

    public static Pot pot;

    public Child(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            Egg egg = pot.removeEgg();
            if (egg == null) {
                return;
            }
            egg.setChild(this);
            table.putEggForColoring(egg);
        }
    }
}
