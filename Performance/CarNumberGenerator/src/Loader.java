import java.io.FileOutputStream;

public class Loader {
    private static final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    public static void main(String[] args) throws Exception {
        StreamingWriter writer1 = new StreamingWriter(letters, new FileOutputStream("res/number1.txt"));
        StreamingWriter writer2 = new StreamingWriter(letters, new FileOutputStream("res/number2.txt"));
        StreamingWriter writer3 = new StreamingWriter(letters, new FileOutputStream("res/number3.txt"));
        StreamingWriter writer4 = new StreamingWriter(letters, new FileOutputStream("res/number4.txt"));
        writer1.start();
        writer2.start();
        writer3.start();
        writer4.start();
    }
}