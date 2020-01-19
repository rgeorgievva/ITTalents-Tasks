package library;

import library.readingMaterials.ReadingMaterial;

public class Client extends Thread {

    public static Library library;

    private ReadingMaterial readingMaterial;

    public Client(String name, ReadingMaterial readingMaterial) {
        super(name);
        this.readingMaterial = readingMaterial;
    }

    @Override
    public void run() {
        try {
            library.rentReadingMaterial(3, readingMaterial);
            Thread.sleep(7000);
            library.giveBack(readingMaterial);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while sleeping.");
        }

    }
}
