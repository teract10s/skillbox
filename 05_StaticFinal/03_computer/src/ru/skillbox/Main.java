package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Computer cp = new Computer("Acer", "G5");
        System.out.println(cp.toString());
        System.out.println("All weight: " + cp.getAllWeight() + " kg" + "\n\n\n");

        cp.setProcessor(new Cpu(12.2, 8, "Intel", 1.0));
        cp.setRandomAccessMemory(new Ram(TypeRAM.DDR2SDRAM, 8, 0.3));
        cp.setMemory(new InformationStorage(TypeInformaionStorage.HDD, 256, 0.25));
        cp.setScreen(new Screen(15.6, TypeScreen.VA, 0.7));
        cp.setKeyboard(new Keyboard(TypeKeyboard.MECHANICS, false, 0.5));

        System.out.println(cp.toString());
        System.out.println("All weight: " + cp.getAllWeight() + " kg");
    }
}
