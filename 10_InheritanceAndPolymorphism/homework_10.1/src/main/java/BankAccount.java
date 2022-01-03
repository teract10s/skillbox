public class BankAccount {
  private double amount;

  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    amount = amountToPut > 0? amount + amountToPut : amount;
  }

  public void take(double amountToTake) {
    amount = amount > amountToTake? amount - amountToTake : amount;
  }
}
