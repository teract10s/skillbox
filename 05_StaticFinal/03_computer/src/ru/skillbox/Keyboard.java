package ru.skillbox;

public class Keyboard {
    private final TypeKeyboard type;
    private final boolean backlight;
    private final double weight;

    public Keyboard(TypeKeyboard type, boolean backlight, double weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public TypeKeyboard getType() {
        return type;
    }

    public boolean isBacklight() {
        return backlight;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "KEYBOARD" + "\n" +
                "Type: " + getType() + "\n" +
                (isBacklight() ? "Has backlight" : "No backlight") + "\n" +
                "Weight: " + getWeight() + " kg" + "\n\n";
    }
}
