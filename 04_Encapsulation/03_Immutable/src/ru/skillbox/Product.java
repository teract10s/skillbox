package ru.skillbox;

public class Product {
    private final String name;
    private int price; // at dollar
    private final String barCode;

    public Product(String name, String barCode) {
        this.name = name;
        this.barCode = barCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public static void main(String[] args) {
        Product juice = new Product("Apple juice", "074682103014");

        juice.setPrice(3);
        System.out.println("You product: " + juice.getName() + "\n" +
                           "Bar code: " + juice.getBarCode() + "\n" +
                           "Price: " + juice.getPrice() + " dollars" + "\n");
    }
}
