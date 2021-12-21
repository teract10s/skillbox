package ru.skillbox;

public class Cargo {
    private final Dimensions dimensions;
    private final double weight;
    private final String address;
    private final boolean canTurn;
    private final String numberOfRegistration;
    private final boolean isFragile;

    public Cargo(Dimensions dimensions, double weight, String address,
                 boolean canTurn, String numberOfRegistration, boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.address = address;
        this.canTurn = canTurn;
        this.numberOfRegistration = numberOfRegistration;
        this.isFragile = isFragile;
    }

    public Cargo withAddress(String address){
        return new Cargo(dimensions, weight, address, canTurn, numberOfRegistration, isFragile);
    }

    public Cargo withWeight(double mass) {
        return new Cargo(dimensions, weight, address, canTurn, numberOfRegistration, isFragile);
    }

    public Cargo withDimensions(Dimensions dimensions){
        return new Cargo(dimensions, weight, address, canTurn, numberOfRegistration, isFragile);
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public boolean isCanTurn() {
        return canTurn;
    }

    public String getNumberOfRegistration() {
        return numberOfRegistration;
    }

    public boolean isFragile() {
        return isFragile;
    }
}
