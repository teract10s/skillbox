package ru.skillbox;

public class ArithmeticCalculator {
    private final int number1;
    private final int number2;
    private final Operation action;

    public ArithmeticCalculator(int number1, int number2, Operation action) {
        this.number1 = number1;
        this.number2 = number2;
        this.action = action;
    }

    int Operation(){
        switch (action){
            case ADD -> {return number1 + number2;}
            case SUBTRACT -> {return number1 - number2;}
            case MULTIPLY -> {return number1 * number2;}
            default -> {return(0);}
        }
    }
}
