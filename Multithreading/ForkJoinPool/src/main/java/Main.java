import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
        CreateListOfLinks listOfLinks = new CreateListOfLinks("https://skillbox.ua/");
        Set<String> paths = new ForkJoinPool().invoke(listOfLinks);
        new CreateFile(paths, "C:\\Users\\user\\Desktop\\das\\out.txt");
    }
}
