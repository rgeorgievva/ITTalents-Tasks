package library.readingMaterials;

import java.time.LocalDate;

public class Book extends ReadingMaterial implements Comparable<Book> {

    public enum Genre implements ReadableSeparator {
        THRILLER, SCI_FI, HORROR, LOVE_STORY;
    }

    private static final int BOOK_MAX_RENT_DURATION = 300; //seconds
    private static final int BOOK_RENT_TAX = 3; //lv

    private String author;
    private Genre genre;

    public Book(String name, String publisher, LocalDate issueDate, String author, Genre genre) {
        super(name, publisher, issueDate);
        this.author = author;
        this.genre = genre;
        this.rentTax = BOOK_RENT_TAX;
        this.initialRentTax = rentTax;
    }

    @Override
    public Type getType() {
        return Type.BOOK;
    }

    @Override
    public int getMaxRentDuration() {
        return BOOK_MAX_RENT_DURATION;
    }

    @Override
    public ReadableSeparator getReadingSeparator() {
        return genre;
    }

    @Override
    public int compareTo(Book book) {
        if (this.getIssueDate().equals(book.getIssueDate())) {
            return 1;
        }
        return this.getIssueDate().compareTo(book.getIssueDate());
    }
}
