package rakidjiinica;

public class Statistician extends Thread {

    private Rakidjiinica rakidjiinica;

    public Statistician(Rakidjiinica rakidjiinica) {
        this.rakidjiinica = rakidjiinica;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("oops");
            }

            this.rakidjiinica.printStatistics();
        }
    }
}
