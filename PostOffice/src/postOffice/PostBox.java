package postOffice;

import postOffice.packets.Letter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostBox {

    private static int currentId = 1;

    private int id;
    private ArrayList<Letter> letters;

    public PostBox() {
        this.letters = new ArrayList<>();
        this.id = currentId++;
    }

    public synchronized void addLetter(Letter letter) {
        this.letters.add(letter);
       // System.out.println("Putting letter in box " + id + ". Total number letters in the box: " + this.letters.size());
    }

    public synchronized List<Letter> empty() {
        ArrayList<Letter> copy = new ArrayList<>(letters);
        letters.clear();
        return Collections.unmodifiableList(copy);
    }
}
