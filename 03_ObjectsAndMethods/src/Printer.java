public class Printer {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.append("The new version adds processing with data", "Report", 13);
        printer.clear();
        printer.append("Это тестовый документ");
        printer.append("I give permission for the processing of my data", 12);

        System.out.println("Pending pages count: " + printer.getPendingPagesCount() + "\n");
        printer.print();
        System.out.println("Pending pages count: " + printer.getPendingPagesCount() + "\n");
        System.out.println("Printed pages count: " + printer.getPrintedPages() + "\n");

    }

    private String queue = "";
    private int countPrintedPages = 0;
    private String namesOfDocuments = "";
    private int countPendingPages = 0;

    public void append(String text){
        append(text, "noname", 1);
    }

    public void append(String text, int countPage){
        append(text, "noname", countPage);
    }

    public void append(String text, String name, int countPage){
        queue +=text + "\n";
        this.countPendingPages += countPage;
        namesOfDocuments += " " + name;
    }

    public void clear(){
        queue = "";
        countPendingPages = 0;
        namesOfDocuments = "";
    }

    public void print(){
        System.out.print("Text of document: " + queue);
        System.out.print("Name of document: " + namesOfDocuments + "\n");
        System.out.println("Number of pages = " + countPendingPages + "\n");
        queue = "";
        namesOfDocuments = "";
        countPrintedPages += countPendingPages;
        countPendingPages = 0;
    }

    public int getPendingPagesCount() {return countPendingPages;}

    public int getPrintedPages(){ return countPrintedPages;}
}
