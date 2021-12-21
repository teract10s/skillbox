package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator example = new ArithmeticCalculator(12, 23, Operation.SUBTRACT);

        System.out.println(example.Operation());
    }
}
