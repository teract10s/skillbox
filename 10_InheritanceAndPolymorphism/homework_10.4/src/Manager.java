public class Manager implements Employee{
    private static final double INTEREST = 0.05;
    private static final int FIX_PRICE = (int) (50_000 + 50_000 * Math.random());
    private int earningForTheCompany = (int) (115_000 + 35_000 * Math.random());

    @Override
    public int getMonthSalary() {
        return (int) (INTEREST * earningForTheCompany + FIX_PRICE);
    }
}
