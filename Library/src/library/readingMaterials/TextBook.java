package library.readingMaterials;

import library.exceptions.InvalidRentException;

import java.time.LocalDate;

public class TextBook extends ReadingMaterial implements Comparable<TextBook> {

    public enum Subject implements ReadableSeparator {
        MATHS, IT, GEOGRAPHY, HISTORY, SCIENCE
    }

    private static final int TEXT_BOOK_MAX_RENT_DURATION = 150; //seconds
    private static final int TEXT_BOOK_RENT_TAX = 2; //lv


    private String author;
    private Subject subject;

    public TextBook(String name, String publisher, LocalDate issueDate, String author, Subject subject) {
        super(name, publisher, issueDate);
        this.author = author;
        this.subject = subject;
        this.rentTax = TEXT_BOOK_RENT_TAX;
        this.initialRentTax = rentTax;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public int getMaxRentDuration() throws InvalidRentException {
        return TEXT_BOOK_MAX_RENT_DURATION;
    }

    @Override
    public ReadableSeparator getReadingSeparator() {
        return subject;
    }

    @Override
    public int compareTo(TextBook textBook) {
        return this.getName().compareTo(textBook.getName());
    }

}
