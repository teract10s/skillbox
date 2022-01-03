public class Main {

    public static void main(String[] args) {
        SchoolUser();
        AdultUser();
        DepositUser();
    }

    private static void SchoolUser(){
        BankAccount schoolUser = new BankAccount();

        schoolUser.put(1000.57);
        System.out.println("Remainder school user: " + schoolUser.getAmount());
        schoolUser.take(500.27);
        System.out.println("Remainder school user: " + schoolUser.getAmount());
    }

    private static void AdultUser(){
        CardAccount adultUser = new CardAccount();

        adultUser.put(1000.57);
        System.out.println("Remainder adult user: " + adultUser.getAmount());
        adultUser.take(500.27);
        System.out.println("Remainder adult user: " + adultUser.getAmount());
    }

    private static void DepositUser(){
        DepositAccount depositAccount = new DepositAccount();

        depositAccount.put(1000.57);
        System.out.println("Remainder adult user: " + depositAccount.getAmount());
        depositAccount.take(500.27);
        System.out.println("Remainder adult user: " + depositAccount.getAmount());
    }
}
