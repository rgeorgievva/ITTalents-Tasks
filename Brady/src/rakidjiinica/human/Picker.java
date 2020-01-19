package rakidjiinica.human;

import rakidjiinica.Fruit;

public class Picker extends Human {

    private Fruit pickedFruit;

    public Picker(String name, int age, Fruit pickedFruit) {
        super(name, age);
        this.pickedFruit = pickedFruit;
    }

    @Override
    public void run() {
        while (true) {
            if (rakidjiinica.isDone()) {
                rakidjiinica.notifyAllWorkers();
                System.out.println(Thread.currentThread().getName() + " terminated!");
                return;
            }
            rakidjiinica.addFruit(this.pickedFruit);
        }
    }
}
