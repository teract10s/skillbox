package ru.skillbox;

public class Screen {
    private final double diagonal;
    private final TypeScreen type;
    private final double weight;

    public Screen(double diagonal, TypeScreen type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public TypeScreen getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "SCREEN" + "\n" +
                "Diagonal: " + getDiagonal() + " in" + "\n" +
                "Type: " + getType() + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
