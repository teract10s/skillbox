package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Computer cp = new Computer("Acer", "G5");
        System.out.println(cp.toString());
        System.out.println("All weight: " + cp.getAllWeight() + " kg" + "\n\n\n");

        cp.withProcessor(new Cpu(12.2, 8, "Intel", 1.0));
        cp.withRandomAccessMemory(new Ram(typeRAM.DDR2SDRAM, 8, 0.3));
        cp.withMemory(new InformationStorage(typeInformaionStorage.HDD, 256, 0.25));
        cp.withScreen(new Screen(15.6, typeScreen.VA, 0.7));
        cp.withKeyboard(new Keyboard(typeKeyboard.MECHANICS, false, 0.5));

        System.out.println(cp.toString());
        System.out.println("All weight: " + cp.getAllWeight() + " kg");
    }
}
