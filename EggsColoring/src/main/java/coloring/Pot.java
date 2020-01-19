package coloring;

import java.util.Stack;

public class Pot {

    private static final int CAPACITY = 50;

    private Stack<Egg> eggs;

    public Pot() {
        this.eggs = new Stack<Egg>();
        for (int i = 0; i < CAPACITY; i++) {
            this.eggs.push(new Egg(Egg.getRandomEggType()));
        }
    }

    public synchronized Egg removeEgg() {
        if (!this.eggs.empty()) {
            return this.eggs.pop();
        }

        return null;
    }
}
