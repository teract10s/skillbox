package ru.skillbox;

public class InformationStorage {
    private final typeInformaionStorage type;
    private final int size;
    private final double weight;

    public InformationStorage(typeInformaionStorage type, int size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public typeInformaionStorage getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String getInformationStorage(){
        return "INFORMATION STORAGE" + "\n" +
                "Type: " + getType() + "\n" +
                "Size: " + getSize() + " Gb" + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
