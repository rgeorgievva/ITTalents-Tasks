package coloring.people;

import coloring.Egg;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Father {

    public void saveEggsInfoToFile(HashMap<Egg.Type, HashSet<Egg>> eggs) {
        File file = new File("statistics");
        try (FileOutputStream fos = new FileOutputStream(file, true);
             PrintStream ps = new PrintStream(fos)
        ){
            for (Map.Entry<Egg.Type, HashSet<Egg>> e : eggs.entrySet()) {
                ps.println(e.getKey() + ":");
                for (Egg egg : e.getValue()) {
                    String colourfulInfo = egg.isColourful() ? "colourful" : "not colourful";
                    ps.println("\t" + egg.getColor() + " - from " + egg.getChild().getName() + " - " + colourfulInfo + " - " +
                            egg.getColouringTime());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        }
    }

}
