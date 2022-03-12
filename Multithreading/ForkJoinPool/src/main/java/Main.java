import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        CreateListOfLinks listOfLinks = new CreateListOfLinks(menu.getUrl());
        Set<String> paths = new ForkJoinPool().invoke(listOfLinks);
        paths.forEach(System.out::println);
        System.out.println(paths.isEmpty());
        CreateFile createFile = new CreateFile(paths, menu.getFilePath());
    }
}
