package library;

import library.exceptions.InvalidRentException;
import library.readingMaterials.ReadingMaterial;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library {

    private class RentLog {
        LocalDateTime start;
        LocalDateTime end;
        String clientName;

        RentLog(LocalDateTime start, LocalDateTime end, String clientName) {
            this.start = start;
            this.end = end;
            this.clientName = clientName;
        }
    }

    private HashMap<ReadingMaterial.Type, HashMap<ReadingMaterial.ReadableSeparator, TreeSet<ReadingMaterial>>> catalogue;
    private HashMap<ReadingMaterial, RentLog> takenMaterials;

    public Library() {
        this.catalogue = new HashMap<>();
        this.takenMaterials = new HashMap<>();
    }

    public void addReadingMaterial(ReadingMaterial material) {
        ReadingMaterial.Type type = material.getType();

        if (!this.catalogue.containsKey(type)) {
            this.catalogue.put(type, new HashMap<>());
        }

        if (!this.catalogue.get(type).containsKey(material.getReadingSeparator())) {
            this.catalogue.get(type).put(material.getReadingSeparator(), new TreeSet<>());
        }

        this.catalogue.get(type).get(material.getReadingSeparator()).add(material);
    }

    public void rentReadingMaterial(int duration, ReadingMaterial material) {
        try {
            if (duration > material.getMaxRentDuration()) {
                System.out.println("Invalid duration!This reading material max rent duration is " +
                        material.getMaxRentDuration() + " seconds.");
                return;
            }
        } catch (InvalidRentException e) {
            System.out.println("Invalid rent!");
            return;
        }

        if (material.isTaken()) {
            System.out.println("Sorry, this reading material is already taken!");
            return;
        }

        material.startRent(duration);
        this.takenMaterials.put(material, new RentLog(LocalDateTime.now(), LocalDateTime.now().plusSeconds(duration),
                Thread.currentThread().getName()));
    }

    public void giveBack(ReadingMaterial material) {
        if (!material.isTaken()) {
            return;
        }

        material.finishRent();
        this.takenMaterials.remove(material);
        System.out.println("You owe " + material.getRentTax());
    }

    public void printNumberAvailableReadingMaterials() {

        int counter = 0;

        for (Map.Entry<ReadingMaterial.Type, HashMap<ReadingMaterial.ReadableSeparator, TreeSet<ReadingMaterial>>> e :
            this.catalogue.entrySet()) {
            for (TreeSet<ReadingMaterial> treeSet : e.getValue().values()) {
                for (ReadingMaterial material : treeSet) {
                    if (!material.isTaken()) {
                        counter++;
                    }
                }
            }
        }

        System.out.println("Number available reading materials: " + counter);
    }

    public void saveCurrentTakenMaterialsByDateOfRent() {
        ArrayList<Map.Entry<ReadingMaterial, RentLog>> listTaken = new ArrayList<>(takenMaterials.entrySet());
        listTaken.sort(new Comparator<Map.Entry<ReadingMaterial, RentLog>>() {
            @Override
            public int compare(Map.Entry<ReadingMaterial, RentLog> t, Map.Entry<ReadingMaterial, RentLog> t1) {
                return t.getValue().start.compareTo(t1.getValue().start);
            }
        });

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formatted = now.format(formatter);
        String fileName = "takenLog" + formatted;
        File file = new File(fileName);

        int counter = 0;

        try (
                FileOutputStream fos = new FileOutputStream(file, true);
                PrintWriter pw = new PrintWriter(fos)
                ){
                for (Map.Entry<ReadingMaterial, RentLog> e : listTaken) {
                    pw.println(e.getKey().getName() + " taken on " + e.getValue().start + ", should be returned on " +
                            e.getValue().end);
                    counter++;
                }

                pw.println("Total number: " + counter);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Problem while writing to file.");
        }
    }

    public void saveAllOverdue() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formatted = now.format(formatter);
        String fileName = "overdueLog" + formatted;
        File file = new File(fileName);

        try (
                FileOutputStream fos = new FileOutputStream(file, true);
                PrintWriter pw = new PrintWriter(fos)
                ) {

            double totalCompensation = 0;

            ArrayList<Map.Entry<ReadingMaterial, RentLog>> overdue = new ArrayList<>();

            pw.println("Reading Material   -   Client Name   -  Accumulated Compensation");
            for (Map.Entry<ReadingMaterial, RentLog> e : takenMaterials.entrySet()) {
                if (e.getValue().end.isBefore(LocalDateTime.now())) {
                    overdue.add(e);
                }
            }

            overdue.sort(new Comparator<Map.Entry<ReadingMaterial, RentLog>>() {
                @Override
                public int compare(Map.Entry<ReadingMaterial, RentLog> t, Map.Entry<ReadingMaterial, RentLog> t1) {
                    double tOverdue = t.getKey().getRentTax() - t.getKey().getInitialRentTax();
                    double t1Overdue = t1.getKey().getRentTax() - t1.getKey().getInitialRentTax();
                    return Double.compare(tOverdue, t1Overdue);
                }
            });

            for (Map.Entry<ReadingMaterial, RentLog> e : overdue) {
                    double compensation = e.getKey().getRentTax() - e.getKey().getInitialRentTax();
                    String clientName = e.getValue().clientName;
                    pw.println(e.getKey().getName() + " - " + clientName + " - " + compensation);
                    totalCompensation += compensation;

            }

            pw.println("Total compensation: " + totalCompensation);
        } catch (FileNotFoundException e) {
            System.out.println("File not found..");
        } catch (IOException e) {
            System.out.println("Problem while writing to file.");
        }
    }
}
