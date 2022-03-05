import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args){
        Bank bank = new Bank();
        int amountOfAccounts = 5;
        List<Account> accounts = createAccountList(amountOfAccounts);
        addAccountsToBank(bank, accounts);
        System.out.println("Sum money at all accounts: " + getSumAtMoneyAccounts(accounts));
        System.out.println(bank);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            threads.add(new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    String from = "User" + (int) (Math.random() * 4);
                    String to = "User" + (int) (Math.random() * 4);
                    int sum = (int) (Math.random() * 52000);
                    try {
                        bank.transfer(from, to, sum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        startThreads(threads);
        joinThreads(threads);
        System.out.println("Sum money at all accounts: " + getSumAtMoneyAccounts(accounts));
    }

    private static void startThreads(List<Thread> threads){
        threads.forEach(Thread::start);
    }

    private static void joinThreads(List<Thread> threads){
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static long getSumAtMoneyAccounts(List<Account> accounts){
        final long[] result = {0};
        for (Account a : accounts) {
            result[0] += a.getMoney();
        }
        return result[0];
    }

    private static List<Account> createAccountList(int amountOfAccounts) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < amountOfAccounts; i++){
            Account account = new Account((long) (Math.random() * (100000L)), "Number" + i);
            accounts.add(account);
        }
        return accounts;
    }

    private static void addAccountsToBank(Bank bank, List<Account> accounts) {
        Map<String, Account> accountMap = new HashMap<>();
        AtomicInteger i = new AtomicInteger();
        accounts.forEach(a -> accountMap.put("User" + i.getAndIncrement(), a));
        bank.setAccounts(accountMap);
    }
}
