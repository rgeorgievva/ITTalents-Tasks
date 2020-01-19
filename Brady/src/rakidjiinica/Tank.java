package rakidjiinica;

import java.util.Random;

public class Tank {

    private static final int CAPACITY = 10;

    private String  name;
    private Fruit currentFruit;
    private int currentKilos;

    public Tank(String name) {
        this.name = name;
    }

    public Fruit getCurrentFruit() {
        return currentFruit;
    }

    public boolean isFull() {
        return this.currentKilos == CAPACITY;
    }

    public boolean isValidFruit(Fruit fruit) {
        if (this.currentFruit == null) {
            this.currentFruit = fruit;
        }

        return this.currentFruit == fruit;
    }

    public void addFruit(Fruit fruit) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("oops");
        }
        this.currentKilos++;
        System.out.println(Thread.currentThread().getName() + " added 1 kilo of " + this.currentFruit + ". Now "
                + this.name + "has " + this.currentKilos + "kg.");
    }

    public int getBrandy() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("oops");
        }
        int kilosBrandy = new Random().nextInt(2) + 1;
        System.out.println(Thread.currentThread().getName() + " boiled " + kilosBrandy + "kg brandy of "
                + this.currentFruit + ".");
        this.currentFruit = null;
        this.currentKilos = 0;

        return kilosBrandy;
    }
}
