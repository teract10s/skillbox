public class Manager
        implements Employee{
    private static final double interest = 0.05;
    int FIX_PRICE = (int) (50_000 + 50_000 * Math.random());
    int EARNING_FOR_THE_COMPANY = (int) (115_000 + 35_000 * Math.random());

    @Override
    public int getMonthSalary() {
        int price = (int) (interest * EARNING_FOR_THE_COMPANY + FIX_PRICE);
        return price;
    }

//    @Override
//    public int compareTo(Employee o) {
//        return Integer.compare(this.getMonthSalary(), o.getMonthSalary());
//    }
}
