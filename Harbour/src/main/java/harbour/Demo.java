package harbour;

public class Demo {

    public static final int NUMBER_SHIPS = 20;

    public static void main(String[] args) {

        Harbour harbour = new Harbour();

        Crane.harbour = harbour;

        Crane firstCrane = new Crane("Crane1");
        Crane secondCrane = new Crane("Crane2");
        Statistician statistician = new Statistician();
        firstCrane.start();
        secondCrane.start();
        statistician.start();


        for (int i = 0; i < NUMBER_SHIPS; i++) {
            Ship ship = new Ship("Ship" + (i + 1));
            ship.arriveAtHarbour(harbour);
        }
    }
}
