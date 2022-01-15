import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final String REGEX_AT_PHONE_NUMBER = "\\+79[0-9]{9}";
    private static final String REGEX_AT_EMAIL = "[a-z]{1,}([.\\-_][a-z]{1,})?@[a-z]{1,}\\.[a-z]{2,3}";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data){
        final int indexName = 0;
        final int indexSurname = 1;
        final int indexEmail = 2;
        final int indexPhone = 3;

        String[] components = data.split("\\s+");

        boolean correctNumber = components[indexPhone].matches(REGEX_AT_PHONE_NUMBER);
        boolean correctEmail = components[indexEmail].matches(REGEX_AT_EMAIL);

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        if (correctEmail && correctNumber) {
            String name = components[indexName] + " " + components[indexSurname];
            storage.put(name, new Customer(name, components[indexPhone], components[indexEmail]));
        }else{
            if (!correctEmail){
                throw new RuntimeException("Wrong email format. \nCorrect email format: vasily.petrov@gmail.com");
            }
            if (!correctNumber){
                throw new RuntimeException("Wrong number format. \nCorrect number format: +79215637722");
            }
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