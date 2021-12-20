package ru.skillbox;

public class InfoAtCargoes {
    private final int width;
    private final int height;
    private final int length;
    private final double mass;
    private final String address;
    private final boolean canTurn;
    private final String numberOfRegistration;
    private final boolean isFragile;

    public InfoAtCargoes(int width, int height, int length, double mass, String address,
                         boolean canTurn, String numberOfRegistration, boolean isFragile) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.mass = mass;
        this.address = address;
        this.canTurn = canTurn;
        this.numberOfRegistration = numberOfRegistration;
        this.isFragile = isFragile;
    }




    public InfoAtCargoes setAddress(String address){
        return new InfoAtCargoes(width, height, length, mass, address, canTurn, numberOfRegistration, isFragile);
    }

    public InfoAtCargoes setMass(double mass){
        return new InfoAtCargoes(width, height, length, mass, address, canTurn, numberOfRegistration, isFragile);
    }

    public InfoAtCargoes setDimensions(int width, int height, int length){
        return new InfoAtCargoes(width, height, length, mass, address, canTurn, numberOfRegistration, isFragile);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public double getMass() {
        return mass;
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
