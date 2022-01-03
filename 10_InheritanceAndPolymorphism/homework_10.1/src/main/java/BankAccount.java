public class BankAccount {
  private double amount;

  protected double getAmount() {
    return amount;
  }

  protected void put(double amountToPut) {
    amount = amountToPut > 0? amount + amountToPut : amount;
  }

  protected void take(double amountToTake) {
    amount = amount > amountToTake? amount - amountToTake : amount;
  }
}
