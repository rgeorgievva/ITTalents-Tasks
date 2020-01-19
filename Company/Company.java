package company;

import java.util.*;

public class Company {

    private static final int MIN_MONTH_SALARY = 900;
    private static final int MAX_MONTH_SALARY = 3000;

    enum Department {

        LEGAL, MARKETING, SALES, HUMAN_RESOURCES, IT, HR, SUPPORT;
    }

    private String name;
    private HashMap<Department, HashSet<Employee>> employeeList;

    public Company(String name) {
        this.name = name;
        this.employeeList = new HashMap<>();
    }

    public void hireEmployee(Employee employee, Department department) {
        if (!this.employeeList.containsKey(department)) {
            this.employeeList.put(department, new HashSet<>());
        }

        this.employeeList.get(department).add(employee);
        employee.setDepartment(department);
    }

    public void listEmployeesByDepartments() {
        System.out.println("===================Company Employees By Departments===================");
        for (Department department : this.employeeList.keySet()) {
            System.out.println();
            System.out.println(department + ":");
            for (Employee employee : this.employeeList.get(department)) {
                System.out.print(employee);
            }
        }
    }

    private TreeSet<Employee> addEmployeesToTreeSet(Comparator<Employee> comparator) {
        TreeSet<Employee> employees = new TreeSet<>(comparator);

        for (Department department : this.employeeList.keySet()) {
            employees.addAll(this.employeeList.get(department));
        }

        return employees;
    }

    public TreeSet<Employee> getEmployeesSortedByHighestSalary() {
        Comparator<Employee> bySalary = (e1, e2) -> Double.compare(e2.getAverageSalary(), e1.getAverageSalary());

        return addEmployeesToTreeSet(bySalary);
    }

    public TreeSet<Employee> getEmployeesSortedByName() {
        Comparator<Employee> byName = (e1, e2) -> {
            if (e1.getName().compareTo(e2.getName()) == 0) {
                return 1;
            }
            return e1.getName().compareTo(e2.getName());
        };

        return addEmployeesToTreeSet(byName);
    }

    public TreeSet<Employee> getEmployeesSortedByAge() {
        Comparator<Employee> byAge = (e1, e2) -> {
            if (e1.getAge() - e2.getAge() == 0) {
                return 1;
            }
            return e1.getAge() - e2.getAge();
        };

        return addEmployeesToTreeSet(byAge);
    }

    public void eliminateDuplicateEmployees() {
        HashSet<Employee> uniqueEmployees = new HashSet<>();
        for (Department department : this.employeeList.keySet()) {
            uniqueEmployees.addAll(this.employeeList.get(department));
        }

        HashMap<Department, HashSet<Employee>> temp = new HashMap<>();

        for (Employee employee : uniqueEmployees) {
            if (!temp.containsKey(employee.getDepartment())) {
                temp.put(employee.getDepartment(), new HashSet<>());
            }
            temp.get(employee.getDepartment()).add(employee);
        }

        this.employeeList = temp;
    }

    public void setSalariesForMonth(Employee.Month month) {
        for (Department department : this.employeeList.keySet()) {
            for (Employee employee : this.employeeList.get(department)) {
                double random = new Random().nextDouble();
                double monthSalary = MIN_MONTH_SALARY + (random * (MAX_MONTH_SALARY - MIN_MONTH_SALARY));
                employee.setSalary(monthSalary, month);

            }
        }
    }
}
