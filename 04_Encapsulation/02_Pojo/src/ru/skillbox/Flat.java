package ru.skillbox;

public class Flat {
    private int square;
    private int countOfRoom;
    private int pricePerMonth; // in dollar

    public Flat(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    private boolean hasBalcony;

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(int countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public static void main(String[] args) {
        Flat pushkinska25A = new Flat(1500);

        pushkinska25A.setHasBalcony(true);
        pushkinska25A.setCountOfRoom(3);
        pushkinska25A.setSquare(80);

        System.out.println("Flat pushkinska 25(a) has " + pushkinska25A.getCountOfRoom() + " rooms" + "\n" +
                "Has " + pushkinska25A.getSquare() + " m^2" + "\n" +
                (pushkinska25A.getHasBalcony() ? "Has balcony" : "Hasn't balcony") + "\n" + "\n" +
                "You need pay " + pushkinska25A.getPricePerMonth() + " dollar per month");
    }
}
