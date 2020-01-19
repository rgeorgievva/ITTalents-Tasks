package rakidjiinica.human;

import rakidjiinica.Fruit;

public class BrandyMaker extends Human {

    private Fruit brandyType;

    public BrandyMaker(String name, int age) {
        super(name, age);
    }

    public void setBrandyType(Fruit brandyType) {
        this.brandyType = brandyType;
    }

    @Override
    public void run() {
        while (true) {
            if (rakidjiinica.isDone()) {
                rakidjiinica.notifyAllWorkers();
                System.out.println(Thread.currentThread().getName() + " terminated!");
                return;
            }
            rakidjiinica.makeBrandy();
        }
    }
}
