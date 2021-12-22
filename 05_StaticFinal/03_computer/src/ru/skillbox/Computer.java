package ru.skillbox;

public class Computer {
    public final String vendor;
    public final String name;
    public Cpu processor;
    public Ram randomAccessMemory;
    public InformationStorage memory;
    public Screen screen;
    public Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
        this.processor = new Cpu(3.2, 8, "Intel", 0.6);
        this.randomAccessMemory = new Ram(typeRAM.BurstEDORAM, 16, 0.2);
        this.memory = new InformationStorage(typeInformaionStorage.SSD, 512, 0.3);
        this.screen = new Screen(15.6, typeScreen.IPS, 0.9);
        this.keyboard = new Keyboard(typeKeyboard.BUTTON, true, 0.7);
    }

    public void withProcessor(Cpu processor){
        this.processor = processor;
    }

    public void withRandomAccessMemory(Ram randomAccessMemory){
        this.randomAccessMemory = randomAccessMemory;
    }

    public void withMemory(InformationStorage memory){
        this.memory = memory;
    }

    public void withScreen(Screen screen){
        this.screen = screen;
    }

    public void withKeyboard(Keyboard keyboard){
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "COMPUTER: " + getVendor() + " " + getName() + "\n\n" +
                processor.getCPU() + randomAccessMemory.getRAM() + memory.getInformationStorage()
                + screen.getScreen() + keyboard.getKeyboard();
    }

    public double getAllWeight(){
        return processor.getWeight() + randomAccessMemory.getWeight() +
               memory.getWeight() + screen.getWeight() + keyboard.getWeight();
    }
}
