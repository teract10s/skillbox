public class TopManager implements Employee{
    Company company = new Company();
    private static final int FIX_PRICE = (int) (75_000 + 50_000 * Math.random());

    @Override
    public int getMonthSalary() {
        int price = FIX_PRICE;
        if (company.getIncome() > 10_000_000){
            price += FIX_PRICE * 1.5;
        }
        return price;
    }

}
