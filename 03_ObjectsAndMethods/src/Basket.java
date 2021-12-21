public class Basket {

    private static double countProduct = 0;
    private static double countBasket = 0;
    private static double totalPriceAtAllBasket;
    private String items;
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public Basket() {
        increaseCountBasket(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static void increaseCountProduct(int count) {
        Basket.countProduct = Basket.countProduct + count;
    }

    public static void increaseCountBasket(int count) {
        Basket.countBasket = Basket.countBasket + count;
    }

    public static void increaseTotalPriceAtAllBasket(int totalPriceAtAllBasket) {
        Basket.totalPriceAtAllBasket = Basket.totalPriceAtAllBasket + totalPriceAtAllBasket;
    }

    public static double averagePriceProduct(){
        return totalPriceAtAllBasket / countProduct;
    }

    public static double averagePriceBasket(){
        return totalPriceAtAllBasket / countBasket;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count, double weight){
        add(name, price, count);
        totalWeight += weight;
    }

    public void add(String name, int price, int count) {
        increaseCountProduct(count);
        increaseTotalPriceAtAllBasket(price*count);
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public double getTotalWeight(){ return totalWeight;}

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }

    public static void main(String[] args) {
        Basket basket1 = new Basket();
        basket1.add("Milk", 40);
        basket1.add("Bear", 57);

        Basket basket2 = new Basket();
        basket2.add("Napkins", 10);
        basket2.add("Watter", 20);

        System.out.println("Average price at product: " + Basket.averagePriceProduct() + "\n" +
                "Average price at basket: " + Basket.averagePriceBasket() + "\n");
    }
}
