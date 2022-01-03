import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    LocalDate lastIncome;

    @Override
    protected void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    protected void take(double amountToTake) {
        LocalDate now = LocalDate.now();
        if (!now.minusMonths(1).isBefore(lastIncome)){
            super.take(amountToTake);
        }
    }
}
