public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        company.hireAll(180, TypeOfWorkers.Operator);
        company.hireAll(80, TypeOfWorkers.Manager);
        company.hireAll(10, TypeOfWorkers.TopManager);

        System.out.println("TOP SALARY STAFF:");
        company.getTopSalaryStaff(15);

        System.out.println("\n\nLOWEST SALARY STAFF:");
        company.getLowestSalaryStaff(30);

        company.fire(50, TypeOfWorkers.Operator);
        company.fire(50, TypeOfWorkers.Manager);
        company.fire(50, TypeOfWorkers.TopManager);

        System.out.println("\n\n\nTOP SALARY STAFF:");
        company.getTopSalaryStaff(15);

        System.out.println("\n\nLOWEST SALARY STAFF:");
        company.getLowestSalaryStaff(30);
    }
}
