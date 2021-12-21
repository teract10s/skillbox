package ru.skillbox;

public class Main {
    public static void main(String[] args) {
        Cargo parcel = new Cargo(new Dimensions(12, 2,3), 12.2,
                "Pushkinska 25 A", true,
                "AS44322", true
        );

        Cargo copy1 = parcel.withAddress("Yangelya 2");
        Cargo copy2 = copy1.withDimensions(new Dimensions(2,3,4));
        Cargo copy = copy2.withWeight(0.24);

        System.out.println("Information about your parcel" + "\n" + "Dimensions: " +
                (copy.getDimensions()).getInfoDimensions() + "\n" +
                "Mass: " + copy.getWeight() + " kg" + "\n" + "Address: " + copy.getAddress() + "\n" +
                (copy.isCanTurn() ? "Can be turned over on delivery" : "Don't can be turned over on delivery") + "\n"
                + "Number of registration: " + copy.getNumberOfRegistration() + "\n" +
                (copy.isFragile() ? "Fragile" : "Not fragile") + "\n");

        System.out.println("Volume - " + (copy.getDimensions()).getVolume() + "*10^3" + " litres");
    }
}
