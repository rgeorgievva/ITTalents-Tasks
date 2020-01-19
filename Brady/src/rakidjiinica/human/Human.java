package rakidjiinica.human;

import rakidjiinica.Rakidjiinica;

public abstract class Human extends Thread {

    public static Rakidjiinica rakidjiinica;
    private String name;
    private int age;

    public Human(String name, int age) {
        super(name);
        this.name = name;
        this.age = age;
    }
}
