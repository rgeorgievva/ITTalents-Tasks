package library;

import library.readingMaterials.Book;
import library.readingMaterials.Magazine;
import library.readingMaterials.TextBook;

import java.time.LocalDate;

public class Demo {

    public static void main(String[] args) {

        Revision revision = new Revision();
        Library library = new Library();
        Revision.library = library;
        revision.start();

        Book book = new Book("One second after", "ciela", LocalDate.now(), "John Matterson",
                Book.Genre.SCI_FI);
        Magazine magazine = new Magazine("Fashion", "ABC", LocalDate.now(), Magazine.Category.FASHION,
              3);
        TextBook textBook = new TextBook("Java za vseki", "Krasimir Stoev", LocalDate.now(),
                "Krasimir Stoev", TextBook.Subject.IT);

        library.addReadingMaterial(book);
        library.addReadingMaterial(magazine);
        library.addReadingMaterial(textBook);

        Client.library = library;
        Client client1 = new Client("Ivan", book);
        Client client2 = new Client("Georgi", textBook);
        client2.start();
        client1.start();

    }
}
