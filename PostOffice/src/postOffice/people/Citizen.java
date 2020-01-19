package postOffice.people;

import postOffice.PostOffice;
import postOffice.packets.Letter;
import postOffice.packets.Packet;
import postOffice.packets.Parcel;

import java.util.Random;

public class Citizen extends Human implements Runnable {

    private String address;

    public Citizen(String firstName, String familyName, String address) {
        super(firstName, familyName);
        this.address = address;
    }

    public void sendLetter() {
        Letter letter = new Letter(this, this);
        PostOffice.getInstance().getRandomPostbox().addLetter(letter);
    }

    public void sendPacket() {
        Random r = new Random();
        Packet packet;
        if (r.nextBoolean()) {
            packet = new Letter(this, this);
        }
        else {
            int packetDimensions = r.nextInt(95) + 5;
            packet = new Parcel(this, this, packetDimensions, packetDimensions, packetDimensions,
                    r.nextBoolean());
        }
        PostOffice.getInstance().receivePacket(packet);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("oops");
            }

            sendLetter();
        }
    }
}
