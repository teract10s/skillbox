public class Operator
        implements Employee{

    int FIX_PRICE = (int) (30_000 + 40_000 * Math.random());

    @Override
    public int getMonthSalary() {
        return FIX_PRICE;
    }
}
