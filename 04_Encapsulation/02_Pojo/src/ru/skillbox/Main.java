package ru.skillbox;

public class Main {
    public static void main(String[] args) {
    Country france = new Country("France");
    // france.setName("France"); так не нужно же прописывать???
    france.setPopulation(67.39); // in million
    france.setSquare(543.94); // in km^2
    france.setCapital("Paris");
    france.setLandlocked(false);

    System.out.println("Name of country: " + france.getName() + "\n" +
                       "Population: " + france.getPopulation() + " million \n" +
                       "Square: " + france.getSquare() + " km^2 \n" +
                       "Capital: " + france.getCapital() + "\n" +
                       (france.getLandlocked() ? "Has no access to the ocean" : "Has access to the ocean"));



    }
}
