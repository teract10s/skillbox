import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;

    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    public void take(double amountToTake) {
        LocalDate now = LocalDate.now();
        if (!now.minusMonths(1).isBefore(lastIncome)){
            super.take(amountToTake);
        }
    }
}
