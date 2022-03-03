import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

class BankTest {
    private static Bank bank = new Bank();

    @BeforeAll
    private static void init() {
        Account account1 = new Account(50000, "number1");
        Account account2 = new Account(10000, "number2");
        Account account3 = new Account(100000, "number3");
        Account account4 = new Account(9999999, "number4");
        account4.setLocked(true);

        Map<String, Account> accountMap = new HashMap<>();

        accountMap.put("User1", account1);
        accountMap.put("User2", account2);
        accountMap.put("User3", account3);
        accountMap.put("User4", account4);

        bank.setAccounts(accountMap);
    }

    @Test
    @DisplayName("Попытка перевести деньги при их недостаточном количестве")
    void transfer1() throws InterruptedException {
        bank.transfer("User2", "User3", 20000);
        Assertions.assertEquals(10000, bank.getAccounts().get("User2").getMoney());
        Assertions.assertEquals(50000, bank.getAccounts().get("User1").getMoney());
    }

    @Test
    @DisplayName("Попытка перевести деньги, когда сумма больше 50000")
    void transfer2() throws InterruptedException {
        bank.transfer("User3", "User1", 70000);
        if (bank.getAccounts().get("User1").isLocked()) {
            Assertions.assertEquals(50000, bank.getAccounts().get("User1").getMoney());
            Assertions.assertEquals(100000, bank.getAccounts().get("User3").getMoney());
        } else {
            Assertions.assertEquals(120000, bank.getAccounts().get("User1").getMoney());
            Assertions.assertEquals(30000, bank.getAccounts().get("User3").getMoney());
        }
    }

    @Test
    @DisplayName("Попытка перевести деньги с заблокированного счета")
    void transfer3() throws InterruptedException {
        bank.transfer("User4", "User2", 20000);
        Assertions.assertEquals(9999999, bank.getAccounts().get("User4").getMoney());
        Assertions.assertEquals(10000, bank.getAccounts().get("User2").getMoney());
    }

    @Test
    @DisplayName("Попытка перевести деньги на заблокированный счет")
    void transfer4() throws InterruptedException {
        bank.transfer("User2", "User4", 20000);
        Assertions.assertEquals(9999999, bank.getAccounts().get("User4").getMoney());
        Assertions.assertEquals(10000, bank.getAccounts().get("User2").getMoney());
    }

    @Test
    @DisplayName("Получение баланса на карте")
    void getBalance() {
        Assertions.assertEquals(10000, bank.getBalance("User2"));
    }

    @Test
    @DisplayName("Получение суммы всех денег на картах")
    void getSumAllAccounts() {
        Assertions.assertEquals(10159999, bank.getSumAllAccounts());
    }
}