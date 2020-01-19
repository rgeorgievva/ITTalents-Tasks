package library.readingMaterials;

import library.exceptions.InvalidRentException;

import java.time.LocalDate;

public class Magazine extends ReadingMaterial implements Comparable<Magazine> {

    public enum Category implements ReadableSeparator {
        FASHION, SCI_FI, NATURE;
    }
    private Category category;

    private int number;
    public Magazine(String name, String publisher, LocalDate issueDate, Category category, int number) {
        super(name, publisher, issueDate);
        this.category = category;
        this.number = number;
    }

    @Override
    public Type getType() {
        return Type.MAGAZINE;
    }

    @Override
    public int getMaxRentDuration() throws InvalidRentException {
        throw new InvalidRentException();
    }

    @Override
    public ReadableSeparator getReadingSeparator() {
        return category;
    }

    @Override
    public int compareTo(Magazine magazine) {
        if (this.getName().equals(magazine.getName())) {
            return this.number - magazine.number;
        }
        return this.getName().compareTo(magazine.getName());
    }
}
