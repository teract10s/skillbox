public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        company.hireAll(180, "Operator");
        company.hireAll(80, "Manager");
        company.hireAll(10, "TopManager");

        System.out.println("TOP SALARY STAFF:");
        company.getLowestSalaryStaff(30);
        System.out.println("\n\nLOWEST SALARY STAFF:");
        company.getTopSalaryStaff(15);

        for (int i = 0; i < 270; i++){
            if (i % 2 != 0){
                company.fire(0);
            }
        }

        System.out.println("\n\n\n");
        System.out.println("TOP SALARY STAFF:");
        company.getLowestSalaryStaff(30);
        System.out.println("\n\nLOWEST SALARY STAFF:");
        company.getTopSalaryStaff(15);

    }
}
