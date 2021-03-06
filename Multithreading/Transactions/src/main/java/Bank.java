import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        synchronized (accounts) {
            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            if (allChecks(fromAccountNum, toAccountNum, amount)) {
                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);
            }
        }
    }

    private boolean allChecks(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount =  accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount.isLocked() || toAccount.isLocked()){
            return false;
        }
        if (amount > 50000) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                accounts.get(fromAccountNum).setLocked(true);
                accounts.get(toAccountNum).setLocked(true);
                return false;
            }
        }
        return fromAccount.getMoney() - amount >= 0;
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        var ref = new Object() {
            long sum = 0;
        };
        accounts.forEach((s, a) -> ref.sum += a.getMoney());
        return ref.sum;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        StringBuilder result= new StringBuilder();
        accounts.forEach((s, a) -> result.append("\nName of account: ").append(s).append(" {\n\t").append(a));
        return result.toString();
    }
}
