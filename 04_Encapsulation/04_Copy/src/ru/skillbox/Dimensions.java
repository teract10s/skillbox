package ru.skillbox;

public class Dimensions {
    private final int width; // at centimeter
    private final int height; // at centimeter
    private final int length; // at centimeter

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public String getInfoDimensions(){
        return width + "*" + height + "*" + length;
    }

    public double getVolume(){
        return (width * height * length);
    }
}
