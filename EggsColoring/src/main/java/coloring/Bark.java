package coloring;

import java.util.ArrayList;

public class Bark {

    private Egg.Type type;
    private ArrayList<Egg> eggs;

    public Bark(Egg.Type type) {
        this.type = type;
        this.eggs = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        this.eggs.add(egg);
    }

    public int getNumberEggs() {
        return this.eggs.size();
    }

    public Egg.Type getType() {
        return type;
    }
}
