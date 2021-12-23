package ru.skillbox;

public class Ram {
    private final TypeRAM type;
    private final int size;
    private final double weight;

    public Ram(TypeRAM type, int size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public TypeRAM getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "RAM" + "\n" +
                "Type: " + getType() + "\n" +
                "Size: " + getSize() + " Gb" + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
