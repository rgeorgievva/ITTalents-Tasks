package company;

public class Demo {

    public static void main(String[] args) {

        Company company = new Company("MyCompany");

        Employee ivan = new Employee("Ivan Ivanov", 22);
        Employee georgi = new Employee("Georgi Georgiev", 30);
        Employee mariya = new Employee("Mariya Dimitrova", 27);
        Employee petyr = new Employee("Petyr Petrov", 35);
        Employee dimityr = new Employee("Dimityr Dimitrov", 40);
        Employee viktoriya = new Employee("Viktoriya Georgieva", 21);
        Employee petya = new Employee("Petya Petrova", 43);
        Employee ivana = new Employee("Ivana Ivanova", 45);
        Employee desislava = new Employee("Desislava Dimitrova", 25);
        Employee desislava2 = new Employee("Desislava Dimitrova", 25);


        //hire employees
        company.hireEmployee(ivan, Company.Department.SUPPORT);
        company.hireEmployee(georgi, Company.Department.SALES);
        company.hireEmployee(mariya, Company.Department.HR);
        company.hireEmployee(petyr, Company.Department.MARKETING);
        company.hireEmployee(dimityr, Company.Department.MARKETING);
        company.hireEmployee(viktoriya, Company.Department.IT);
        company.hireEmployee(petya, Company.Department.HR);
        company.hireEmployee(ivana, Company.Department.SALES);
        company.hireEmployee(desislava, Company.Department.IT);
        company.hireEmployee(desislava2, Company.Department.MARKETING);

        //set salaries for all the employees for each month of the year
        company.setSalariesForMonth(Employee.Month.JAN);
        company.setSalariesForMonth(Employee.Month.FEB);
        company.setSalariesForMonth(Employee.Month.MAR);
        company.setSalariesForMonth(Employee.Month.APR);
        company.setSalariesForMonth(Employee.Month.MAY);
        company.setSalariesForMonth(Employee.Month.JUN);
        company.setSalariesForMonth(Employee.Month.JUL);
        company.setSalariesForMonth(Employee.Month.AUG);
        company.setSalariesForMonth(Employee.Month.SEP);
        company.setSalariesForMonth(Employee.Month.OCT);
        company.setSalariesForMonth(Employee.Month.NOV);
        company.setSalariesForMonth(Employee.Month.DEC);

        //print all the employees by departments
        company.listEmployeesByDepartments();
        System.out.println();

        //print all the employees sorted by highest average salary
        System.out.println("=======Employees ordered by average salary=======");
        System.out.println(company.getEmployeesSortedByHighestSalary());
        System.out.println();

        //print all the employees sorted by their name
        System.out.println("=======Employees ordered by name=======");
        System.out.println(company.getEmployeesSortedByName());
        System.out.println();

        //print all the employees sorted by their age
        System.out.println("=======Employees ordered age=======");
        System.out.println(company.getEmployeesSortedByAge());
        System.out.println();

        //eliminate the employees with equal name and age
        System.out.println("Eliminating employees with equal name and age...");
        company.eliminateDuplicateEmployees();

        //print all the employees by departments -> they should be unique
        company.listEmployeesByDepartments();
        System.out.println();

        //print all salaries for the year of employee Viktoriya Gerorgieva
        viktoriya.printAllSalaries();

    }
}
