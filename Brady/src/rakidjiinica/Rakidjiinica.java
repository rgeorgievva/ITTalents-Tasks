package rakidjiinica;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Rakidjiinica {

    private static final int NUMBER_TANKS = 5;
    public static final int AMOUNT_TO_ACHIEVE = 10;
    private ArrayList<Tank> tanks;
    private HashMap<Fruit, Integer> brandy;
    private HashMap<Fruit, Integer> pickedFruits;
    private int totalKilosOfBrandy;
    
    public Rakidjiinica() {
        this.tanks = new ArrayList<>();
        for (int i = 0; i < NUMBER_TANKS; i++) {
            this.tanks.add(new Tank("TANK " + (i + 1)));
        }
        this.totalKilosOfBrandy = 0;
        this.brandy = new HashMap<>();
        this.pickedFruits = new HashMap<>();
    }
    
    private Tank getTankForFilling(Fruit fruitToFillWith) {
        for (Tank tank : this.tanks) {
            if (!tank.isFull() && tank.isValidFruit(fruitToFillWith)) {
                return tank;
            }
        }
        
        return null;
    }
    
    public synchronized void addFruit(Fruit pickedFruit) {
        Tank tank = getTankForFilling(pickedFruit);
        
        while (tank == null && !this.isDone()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
            tank = getTankForFilling(pickedFruit);
        }

        if (isDone()) {
            return;
        }

        tank.addFruit(pickedFruit);
        this.addPickedFruit(pickedFruit);
        notifyAll();
    }

    private void addPickedFruit(Fruit pickedFruit) {
        if (!this.pickedFruits.containsKey(pickedFruit)) {
            this.pickedFruits.put(pickedFruit, 1);
        }
        else {
            this.pickedFruits.put(pickedFruit, this.pickedFruits.get(pickedFruit) + 1);
        }
    }

    private Tank getTankForEmptying() {
        for (Tank tank : this.tanks) {
            if (tank.isFull()) {
                return tank;
            }
        }
        
        return null;
    }
    
    public synchronized void makeBrandy() {
        Tank tank = getTankForEmptying();
        
        while (tank == null && !this.isDone()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
            tank = getTankForEmptying();
        }

        if (isDone()) {
            return;
        }

        this.addBrandy(tank.getCurrentFruit());
        this.totalKilosOfBrandy += tank.getBrandy();
        this.notifyAll();
    }

    private void addBrandy(Fruit fruit) {
        if (!this.brandy.containsKey(fruit)) {
            this.brandy.put(fruit, 1);
        }
        else {
            this.brandy.put(fruit, this.brandy.get(fruit) + 1);
        }
    }

    public boolean isDone() {
        return this.totalKilosOfBrandy >= AMOUNT_TO_ACHIEVE;
    }

    public synchronized void notifyAllWorkers() {
        notifyAll();
    }

    public void printStatistics() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        String fileName = "log" + formattedTime + ".txt";

        File statistics = new File(fileName);

        try (
                FileOutputStream fos = new FileOutputStream(statistics, true);
                PrintStream ps = new PrintStream(fos);
                ){
            ps.println("=======" + now.getHour() + "h:" + now.getMinute() + "m:" + now.getSecond() + "s" + "=======");
            ps.println();
            ps.println(mostPickedFruit());
            ps.println(mostProducedBrandyType());
            ps.println(grapesBrandyToApricotBrandy());

        } catch (FileNotFoundException e) {
            System.out.println("File not found..");
        } catch (IOException e) {
            System.out.println("Problem while writing to a file..");
        }

    }

    private String  mostProducedBrandyType() {
        int maxKilos = 0;
        Fruit mostProducedType = null;

        for (Fruit fruit : this.brandy.keySet()) {
            if (this.brandy.get(fruit) > maxKilos) {
                maxKilos = this.brandy.get(fruit);
                mostProducedType = fruit;
            }
        }

        String result = null;
        if (mostProducedType == null) {
            result = "No brandy produced yet";
        }
        else {
            result = "Most produced type of brandy " + mostProducedType + " - " + maxKilos;
        }

        return result;
    }

    private String  mostPickedFruit() {
        int maxKilos = 0;
        Fruit mostPicked = null;

        for (Fruit fruit : this.pickedFruits.keySet()) {
            if (this.pickedFruits.get(fruit) > maxKilos) {
                maxKilos = this.pickedFruits.get(fruit);
                mostPicked = fruit;
            }
        }

        return "Most picked fruit: " + mostPicked + " - " + maxKilos + "kg";
    }

    private String  grapesBrandyToApricotBrandy() {
        int grapesBrandyKg = this.brandy.get(Fruit.GRAPES) == null ? 0 : this.brandy.get(Fruit.GRAPES);
        int apricotBrandyKg = this.brandy.get(Fruit.APRICOT) == null ? 0 : this.brandy.get(Fruit.APRICOT);

        return "GRAPES BRANDY LITERS : APRICOT BRANDY LITERS : " + grapesBrandyKg + " / " + apricotBrandyKg;
    }
}
