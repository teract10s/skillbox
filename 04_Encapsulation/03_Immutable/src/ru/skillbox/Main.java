package ru.skillbox;

public class Main {
    public static void main(String[] args) {
        Book dune = new Book("Dune", "Frank Herbert", 412, "9780593099322");

        System.out.println("You book: " + dune.getName() + "\n" +
                           "Author: " + dune.getAuthor() + "\n" +
                           dune.getNumberOfPages() + " pages" + "\n" +
                           "You can search his at ISBN number - " + dune.getNumberISBN() + "\n");
    }
}
