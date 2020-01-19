package library;

public class Revision extends Thread {

        public static Library library;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);//31 days
                library.printNumberAvailableReadingMaterials();
                library.saveCurrentTakenMaterialsByDateOfRent();
                library.saveAllOverdue();
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
        }
    }
}
