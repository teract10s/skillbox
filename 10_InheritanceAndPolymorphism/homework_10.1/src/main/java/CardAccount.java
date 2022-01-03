public class CardAccount extends BankAccount {
    private static final double COMMISSIONS = 0.01;

    @Override
    protected void take(double amountToTake) {
        amountToTake += amountToTake * COMMISSIONS;
        super.take(amountToTake);
    }
}
