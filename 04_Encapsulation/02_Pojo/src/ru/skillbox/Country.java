package ru.skillbox;

public class Country {
    private String name;
    private double population;
    private double square;
    private String capital;
    private boolean landlocked;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean getLandlocked() {
        return landlocked;
    }

    public void setLandlocked(boolean landlocked) {
        this.landlocked = landlocked;
    }


}
