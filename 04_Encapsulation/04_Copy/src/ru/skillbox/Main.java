package ru.skillbox;

public class Main {
    public static void main(String[] args) {
        InfoAtCargoes parcel = new InfoAtCargoes(12, 2, 3, 12.2,
                "Pushkinska 25 A", true,
                "AS44322", true
        );

        InfoAtCargoes copy1 = parcel.setAddress("Yangelya 2");
        InfoAtCargoes copy2 = copy1.setDimensions(2,3,4);
        InfoAtCargoes copy = copy2.setMass(0.24);

        System.out.println("Information about your parcel" + "\n" + "Dimensions: " + copy.getWidth() + "*" +
                copy.getHeight() + "*" +  copy.getLength() + "\n" +
                "Mass: " + copy.getMass() + " kg" + "\n" + "Address: " + copy.getAddress() + "\n" +
                (copy.isCanTurn() ? "Can be turned over on delivery" : "Don't can be turned over on delivery") + "\n"
                + "Number of registration: " + copy.getNumberOfRegistration() + "\n" +
                (copy.isFragile() ? "Fragile" : "Not fragile") + "\n");

        Dimensions volume = new Dimensions(copy.getWidth(), copy.getHeight(), copy.getLength());
        System.out.println("Volume - " + volume.getVolume() + "*10^3" + " litres");
    }
}
