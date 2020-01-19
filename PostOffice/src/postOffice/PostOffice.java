package postOffice;

import postOffice.packets.Letter;
import postOffice.packets.Packet;
import postOffice.people.Gatherer;
import postOffice.people.Mailman;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PostOffice {

    private static final int NUMBER_MAILMEN = 10;
    private static final int NUMBER_GATHERERS = 10;
    private static final int NUMBER_BOXES = 25;
    public static final int POST_OFFICE_MIN_NUMBER_PACKETS = 50;

    private static PostOffice instance;
    private LinkedList<Packet> repository;
    private TreeMap<LocalDate, TreeMap<LocalTime, Packet>> archive;
    private ArrayList<Mailman> mailmen;
    private ArrayList<Gatherer> gatherers;
    private ArrayList<PostBox> boxes;

    private PostOffice() {
        this.repository = new LinkedList<>();
        this.archive = new TreeMap<>();
        hireMailmen();
        hireGatherers();
        putPostBoxes();
    }

    public synchronized static PostOffice getInstance() {
        if (instance == null) {
            instance = new PostOffice();
        }

        return instance;
    }

    private void putPostBoxes() {
        this.boxes = new ArrayList<>();
        for (int i = 0; i < NUMBER_BOXES; i++) {
            this.boxes.add(new PostBox());
        }
    }

    private void hireMailmen() {
        this.mailmen = new ArrayList<>();
        for (int i = 0; i < NUMBER_MAILMEN; i++) {
            Mailman mailman = new Mailman("Mailman" + (i + 1) , "Ivanov",
                    new Random().nextInt(4) + 3);
            mailmen.add(mailman);
            new Thread(mailman).start();
        }
    }

    private void hireGatherers() {
        this.gatherers = new ArrayList<>();
        for (int i = 0; i < NUMBER_GATHERERS; i++) {
            Gatherer gatherer = new Gatherer("Gatherer" + (i + 1) , "Georgiev",
                    new Random().nextInt(2) + 1);
            gatherers.add(gatherer);
            new Thread(gatherer).start();
        }
    }

    public PostBox getRandomPostbox() {
        Collections.shuffle(this.boxes);
        return this.boxes.get(0);
    }

    public synchronized void receivePacket(Packet packet) {
        this.repository.add(packet);

        LocalDate currentDate = LocalDate.now();

        if (!this.archive.containsKey(currentDate)) {
            this.archive.put(currentDate, new TreeMap<>());
        }

        this.archive.get(currentDate).put(LocalTime.now(), packet);
    }

    public synchronized void sendGatherersToPickLetters() {
        try {
            while (this.repository.size() >= POST_OFFICE_MIN_NUMBER_PACKETS) {
                wait();
            }
            System.out.println("=========================");
            ArrayList<Letter> gatheredLetters = gatherLetters();
            Thread.sleep(3000);//2 hours
            for (int i = 0; i < gatheredLetters.size(); i++) {
                receivePacket(gatheredLetters.get(i));
            }
            System.out.println("Gatherer gathered " + gatheredLetters.size() + " number of letters. " +
                    "Letters in repo now:"  + this.repository.size());
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println("oops");
        }
    }

    private ArrayList<Letter> gatherLetters() {
        ArrayList<Letter> letters = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            List<Letter> gatheredLetters = this.boxes.get(i).empty();
            letters.addAll(gatheredLetters);
        }

        return letters;
    }

    public synchronized void deliverPackets(Mailman mailman) {
        try {
            while (this.repository.size() < POST_OFFICE_MIN_NUMBER_PACKETS) {
                wait();
            }

            mailman.setBusy(true);
            int numberFreeMailmen = getFreeMailmenNumber();
            int numberPacketsPerMailman = this.repository.size() / numberFreeMailmen;

            for (int i = 0; i < numberPacketsPerMailman; i++) {
                Packet packet = this.repository.removeFirst();
                Thread.sleep(10);
            }
            System.out.println(mailman.getFirstName() + " delivered " + numberPacketsPerMailman + " packets.");
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println("oops");
        }


    }

    private int getFreeMailmenNumber() {
        int freeMailmen = 0;
        for (int i = 0; i < this.mailmen.size(); i++) {
            if (!this.mailmen.get(i).isBusy()) {
                freeMailmen++;
            }
        }

        return freeMailmen;
    }
}
