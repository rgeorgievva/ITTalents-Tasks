package coloring;

import java.util.ArrayList;

public class Table {

    private ArrayList<Jar> jars;

    public Table() {
        this.jars = new ArrayList<>();
        this.jars.add(new Jar(Jar.Color.ORANGE));
        this.jars.add(new Jar(Jar.Color.BLUE));
        this.jars.add(new Jar(Jar.Color.GREEN));
        this.jars.add(new Jar(Jar.Color.RED));
        this.jars.add(new Jar(Jar.Color.YELLOW));
    }

    private Jar getFreeJar() {
        for (Jar jar : this.jars) {
            if (!jar.isFull()) {
                return jar;
            }
        }

        return null;
    }

    public synchronized void putEggForColoring(Egg egg) {
        Jar jar = getFreeJar();
        while (jar == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
            jar = getFreeJar();
        }

        System.out.println(Thread.currentThread().getName() + " puts " + egg.getType() + " egg for colouring in a jar.");
        jar.addEgg(egg);
        egg.setColor(jar.getColor());
        this.notifyAll();
    }

    private Jar getJarWithColouredEgg() {
        for (Jar jar : this.jars) {
            if (jar.getColouredEgg() != null) {
                return jar;
            }
        }

        return null;
    }

    public synchronized Egg removeEgg() {
        Jar jar = getJarWithColouredEgg();
        while (jar == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("NEW COLOURED EGGS.MOTHER WAKE UP!");
            }
            jar = getJarWithColouredEgg();
        }

        Egg egg = jar.removeEgg();
        notifyAll();
        return egg;
    }
}
