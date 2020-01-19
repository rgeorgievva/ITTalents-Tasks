package coloring;

import java.util.ArrayList;

public class Jar {

    public enum Color {

        GREEN, RED, BLUE, YELLOW, ORANGE
    }

    private static final int CAPACITY = 2;

    private Color color;
    private ArrayList<Egg> eggs;

    public Jar(Color color) {
        this.color = color;
        this.eggs = new ArrayList<>();
    }

    public Color getColor() {
        return color;
    }

    public void addEgg(Egg egg) {
        this.eggs.add(egg);
        egg.startColouring();
    }

    public Egg removeEgg() {
        Egg egg = getColouredEgg();
        this.eggs.remove(egg);

        return egg;
    }

    public boolean isFull() {
        return this.eggs.size() == CAPACITY;
    }

    public Egg getColouredEgg() {
        if (this.eggs.size() == 0) {
            return null;
        }

        if (this.eggs.get(0).hasColour()) {
            return this.eggs.get(0);
        }

        if (this.eggs.size() > 1 && this.eggs.get(1).hasColour()) {
            return this.eggs.get(1);
        }

        return null;
    }
}
