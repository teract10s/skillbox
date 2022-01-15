import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final String REGEX_AT_PHONE_NUMBER = "\\+79[0-9]{9}";
    private static final String REGEX_AT_PHONE_EMAIL = "[a-z]{1,}([.\\-_][a-z]{1,})?@[a-z]{1,}\\.[a-z]{2,3}";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

        boolean correctNumber = components[INDEX_PHONE].matches(REGEX_AT_PHONE_NUMBER);
        boolean correctEmail = components[INDEX_EMAIL].matches(REGEX_AT_PHONE_EMAIL);

        if (correctEmail && correctNumber) {
            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
        }else{
            String message = "";
            message += correctEmail ? "" : "Wrong email format. \nCorrect email format: vasily.petrov@gmail.com";
            message += correctNumber ? "" : "Wrong number format. \nCorrect number format: +79215637722";
            System.out.println(message);
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}