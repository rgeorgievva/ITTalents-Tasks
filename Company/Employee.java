package company;

import java.util.Objects;
import java.util.TreeMap;

public class Employee {

    enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;
    }

    private static final int NUMBER_MONTHS_IN_YEAR = 12;

    private static int idNumberNextEmployee = 1;

    private String name;
    private int age;
    private TreeMap<Month, Double> salaries;
    private int idNumber;
    private Company.Department department;
    private double averageSalary;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.idNumber = Employee.idNumberNextEmployee++;
        this.salaries = new TreeMap<>();
        addSalaries();
        setAverageSalary();
    }

    private void addSalaries() {
        salaries.put(Month.JAN, 0.0);
        salaries.put(Month.FEB, 0.0);
        salaries.put(Month.MAR, 0.0);
        salaries.put(Month.APR, 0.0);
        salaries.put(Month.MAY, 0.0);
        salaries.put(Month.JUN, 0.0);
        salaries.put(Month.JUL, 0.0);
        salaries.put(Month.AUG, 0.0);
        salaries.put(Month.SEP, 0.0);
        salaries.put(Month.OCT, 0.0);
        salaries.put(Month.NOV, 0.0);
        salaries.put(Month.DEC, 0.0);
    }

    public double getAverageSalary() {
        return this.averageSalary;
    }

    private void setAverageSalary() {
        double sum = 0;
        for (Month month : this.salaries.keySet()) {
            sum += this.salaries.get(month);
        }

        this.averageSalary = sum / NUMBER_MONTHS_IN_YEAR;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Company.Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Company.Department department) {
        this.department = department;
    }

    public void setSalary(double salary, Month month) {
        this.salaries.put(month, salary);
        setAverageSalary();
    }

    public void printAllSalaries() {
        System.out.println("=======" + this.name + " SALARIES=======");
        for (Month month : this.salaries.keySet()) {
            System.out.println(month + ": " + this.salaries.get(month));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return String.format("%s    Age: %d   ID Number: %d   Average salary: %.2f \n", this.name, this.age, this.idNumber, this.averageSalary);
    }
}
