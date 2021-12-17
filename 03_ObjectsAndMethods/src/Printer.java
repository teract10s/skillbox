public class Printer {
    private String queue = "";
    private int countPage = 0;
    static int countPrintedPages = 0;
    private String namesDocuments = "";

    public void append(String text){
        append(text, "noname", 1);
    }

    public void append(String text, int countPage){
        append(text, "noname", countPage);
    }

    public void append(String text, String name, int countPage){
        queue +=text + "\n";
        this.countPage += countPage;
        namesDocuments += name;
    }

    public void clear(){
        queue = "";
        countPage = 0;
        namesDocuments = "";
    }

    public void print(){
        System.out.print("Text of document: " + queue);
        System.out.print("Name of document: " + namesDocuments + "\n");
        System.out.println("Number of pages = " + countPage + "\n");
        queue = "";
        namesDocuments = "";
        countPrintedPages += countPage;
        countPage = 0;
    }

    public static int getPrintedPages(){ return countPrintedPages;}
}
