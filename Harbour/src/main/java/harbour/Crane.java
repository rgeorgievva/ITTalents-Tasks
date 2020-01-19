package harbour;

public class Crane extends Thread {

    public static Harbour harbour;
    private static int currentId = 1;

    private String name;
    private int id;

    public Crane(String name) {
        super(name);
        this.id = currentId++;
        this.name = name;
    }

    public int getCraneId() {
        return id;
    }

    @Override
    public void run() {
        while (true) {
            harbour.unloadShip(this);
        }
    }

}
