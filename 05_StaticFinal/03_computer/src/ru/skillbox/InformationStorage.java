package ru.skillbox;

public class InformationStorage {
    private final TypeInformaionStorage type;
    private final int size;
    private final double weight;

    public InformationStorage(TypeInformaionStorage type, int size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public TypeInformaionStorage getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "INFORMATION STORAGE" + "\n" +
                "Type: " + getType() + "\n" +
                "Size: " + getSize() + " Gb" + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
