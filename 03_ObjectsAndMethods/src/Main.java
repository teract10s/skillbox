public class Main {
    public static void main(String[] args) {
        Basket basket1 = new Basket();
        basket1.add("Milk", 40);
        basket1.add("Bear", 50);

        Basket basket2 = new Basket();
        basket2.add("Napkins", 10);
        basket2.add("Watter", 20);

        System.out.println("Average price at product: " + Basket.averagePriceProduct() + "\n" +
                "Average price at basket: " + Basket.averagePriceBasket() + "\n");
    }
}
