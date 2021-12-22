package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator example = new ArithmeticCalculator(12, 23);

        System.out.println("number1 + number2 = " + example.calculate(Operation.ADD));
        System.out.println("number1 - number2 = " + example.calculate(Operation.SUBTRACT));
        System.out.println("number1 * number2 = " + example.calculate(Operation.MULTIPLY));
    }
}
