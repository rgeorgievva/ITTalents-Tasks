package coloring;

import coloring.db.EggDAO;
import coloring.people.Father;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Refrigerator {

    public static Father father;

    private ArrayList<Bark> barks;
    private HashMap<Egg.Type, HashSet<Egg>> eggs;

    public Refrigerator() {
        this.barks = new ArrayList<>();
        this.barks.add(new Bark(Egg.Type.CHICKEN));
        this.barks.add(new Bark(Egg.Type.GOOSE));
        this.barks.add(new Bark(Egg.Type.DUCK));
        this.eggs = new HashMap<>();
        this.eggs.put(Egg.Type.CHICKEN, new HashSet<>());
        this.eggs.put(Egg.Type.GOOSE, new HashSet<>());
        this.eggs.put(Egg.Type.DUCK, new HashSet<>());
    }

    public synchronized void putEgg(Egg egg) {
        Bark bark = getBarkForEgg(egg);
        bark.addEgg(egg);
        this.eggs.get(egg.getType()).add(egg);
        father.saveEggsInfoToFile(eggs);
        try {
            EggDAO.getInstance().saveEggInfo(egg);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " put " + egg.getType() + " egg in the refrigerator." +
                "Eggs in " + bark.getType() + " now: " + bark.getNumberEggs());

    }

    private Bark getBarkForEgg(Egg egg) {
        for (Bark bark : this.barks) {
            if (bark.getType().equals(egg.getType())) {
                return bark;
            }
        }
        return null;
    }

    public int getTotalEggs() {
        int total = 0;
        for (Bark bark : this.barks) {
            total += bark.getNumberEggs();
        }

        return total;
    }
}
