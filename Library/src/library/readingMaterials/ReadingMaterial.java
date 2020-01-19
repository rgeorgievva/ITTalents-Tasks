package library.readingMaterials;

import library.exceptions.InvalidRentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

public abstract class ReadingMaterial {

    public enum Type {
        BOOK, MAGAZINE, TEXT_BOOK
    }

    public interface ReadableSeparator {

    }

    private class Inspector extends Thread {

        int duration;

        Inspector(int duration) {
            this.duration = duration;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(duration * 1000);
                if (isTaken()) {
                    while (true) {
                        Thread.sleep(1000);
                        rentTax += rentTax * 0.01;
                        System.out.println("Accumulating interest of 1%. The tax now is " + rentTax);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("End of accumulating interest!");
            }
        }
    }


    private Inspector inspector;
    private String name;
    private String publisher;
    private LocalDate issueDate;
    private TreeMap<LocalDateTime, LocalDateTime> rentHistory;
    protected double rentTax = 0;
    protected double initialRentTax = 0;

    public ReadingMaterial(String name, String publisher, LocalDate issueDate) {
        this.name = name;
        this.publisher = publisher;
        this.issueDate = issueDate;
        this.rentHistory = new TreeMap<>();
    }

    public abstract Type getType();

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getName() {
        return name;
    }

    public void startRent(int duration) {
        this.inspector = new Inspector(duration);
        this.rentHistory.put(LocalDateTime.now(), null);
        inspector.start();
    }

    public void finishRent() {
        inspector.interrupt();
        this.rentHistory.put(this.rentHistory.lastEntry().getKey(), LocalDateTime.now());
    }

    public abstract int getMaxRentDuration() throws InvalidRentException;

    public boolean isTaken() {
        if (this.rentHistory.isEmpty()) {
            return false;
        }

        return this.rentHistory.lastEntry().getValue() == null;
    }

    public double getRentTax() {
        return rentTax;
    }

    public double getInitialRentTax() {
        return initialRentTax;
    }

    public abstract ReadableSeparator getReadingSeparator();
}
