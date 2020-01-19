package coloring;

import coloring.people.Child;
import coloring.people.Father;
import coloring.people.Mother;
import coloring.people.Person;

public class Demo {

    public static void main(String[] args) {
        Pot pot = new Pot();
        Table table = new Table();
        Refrigerator refrigerator = new Refrigerator();
        Refrigerator.father = new Father();

        Mother mother = new Mother("Mother");
        mother.start();

        Egg.mother = mother;

        Person.table = table;
        Mother.refrigerator = refrigerator;
        Child.pot = pot;

        for (int i = 0; i < 3; i++) {
            Child child = new Child("Child" + (i + 1));
            child.start();
        }

    }
}
