package ru.skillbox;

public class ArithmeticCalculator {
    private final int number1;
    private final int number2;

    public ArithmeticCalculator(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int calculate(Operation action){
        switch (action){
            case ADD -> {return number1 + number2;}
            case SUBTRACT -> {return number1 - number2;}
            case MULTIPLY -> {return number1 * number2;}
            default -> {return(0);}
        }
    }
}
