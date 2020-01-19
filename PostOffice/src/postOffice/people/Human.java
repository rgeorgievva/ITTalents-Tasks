package postOffice.people;

public abstract class Human {

    private String firstName;
    private String familyName;

    public Human(String firstName, String familyName) {
        this.firstName = firstName;
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }
}
