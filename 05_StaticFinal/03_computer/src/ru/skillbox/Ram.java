package ru.skillbox;

public class Ram {
    private final typeRAM type;
    private final int size;
    private final double weight;

    public Ram(typeRAM type, int size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public typeRAM getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String getRAM(){
        return "RAM" + "\n" +
                "Type: " + getType() + "\n" +
                "Size: " + getSize() + " Gb" + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
