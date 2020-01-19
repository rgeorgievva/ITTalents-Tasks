package coloring.people;

import coloring.Table;

public class Person extends Thread {

    public static Table table;

    private String name;

    public Person(String name) {
        super(name);
        this.name = name;
    }
}
