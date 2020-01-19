package coloring;

import coloring.people.Child;
import coloring.people.Mother;

import java.time.LocalDateTime;
import java.util.Random;

public class Egg {

    public enum Type {
        CHICKEN(3), GOOSE(2), DUCK(1);

        private int timeForColouring;

        Type(int timeForColouring) {
            this.timeForColouring = timeForColouring;
        }
    }

    private class Supervisor extends Thread {

        private Egg egg;

        private Supervisor(Egg egg) {
            this.egg = egg;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(egg.getTimeForColoring() * 1000);
                egg.colour();
                System.out.println("Egg is COLOURED!");
                mother.interrupt();

            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    public static Mother mother;
    private Type type;
    private boolean isColourful;
    private boolean hasColour;
    private Supervisor supervisor;
    private Child child;
    private LocalDateTime colouringTime;
    private Jar.Color color;

    public Egg(Type type) {
        this.type = type;
        this.isColourful = false;
        this.hasColour = false;
        this.supervisor = new Supervisor(this);
    }

    public void setColor(Jar.Color color) {
        this.color = color;
    }

    public Jar.Color getColor() {
        return color;
    }

    public void setColouringTime(LocalDateTime colouringTime) {
        this.colouringTime = colouringTime;
    }

    public LocalDateTime getColouringTime() {
        return colouringTime;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Child getChild() {
        return child;
    }

    public Type getType() {
        return type;
    }

    public void startColouring() {
        this.supervisor.start();
    }

    public void setColourful() {
        this.isColourful = true;
    }

    public boolean isColourful() {
        return isColourful;
    }

    public int getTimeForColoring() {
        return type.timeForColouring;
    }

    public void colour() {
        this.hasColour = true;
        this.colouringTime = LocalDateTime.now();
    }

    public boolean hasColour() {
        return hasColour;
    }

    public static Type getRandomEggType() {
        Random r = new Random();
        int eggTypeChance = r.nextInt(3);

        switch (eggTypeChance) {
            case 0:
                return Type.CHICKEN;
            case 1:
                return Type.GOOSE;
            case 2:
                return Type.DUCK;
            default:
                return null;
        }
    }
}
