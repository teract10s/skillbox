package ru.skillbox;

public class Cpu {
    private final double frequency;
    private final int numberOfCores;
    private final String vendor;
    private final double weight;

    public Cpu(double frequency, int numberOfCores, String vendor, double weight) {
        this.frequency = frequency;
        this.numberOfCores = numberOfCores;
        this.vendor = vendor;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public String getVendor() {
        return vendor;
    }

    public double getWeight() {
        return weight;
    }

    public String getCPU(){
        return  "CPU" + "\n" +
                "Frequency: " + getFrequency() + " GHz" + "\n" +
                "Number of cores: " + getNumberOfCores() + "\n" +
                "Vendor: " + getVendor() + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
