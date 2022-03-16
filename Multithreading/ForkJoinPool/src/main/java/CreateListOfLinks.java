import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

@Getter
public class CreateListOfLinks extends RecursiveTask<Set<String>> {
    private static final Set<String> paths = new HashSet<>();
    private final String url;

    public CreateListOfLinks(String url) {
        this.url = url;
    }

    @Override
    protected Set<String> compute() {
        paths.add(url);
        
        List<CreateListOfLinks> taskList = new ArrayList<>();

        try {
           Set<String> currentPaths = getCurrentPaths(url);

           for (String str : currentPaths){
               CreateListOfLinks task = new CreateListOfLinks(str);
               task.fork();
               taskList.add(task);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        taskList.forEach(CreateListOfLinks::join);

        return paths;
    }

    private Set<String> getCurrentPaths(String url) throws IOException, InterruptedException {
        Set<String> currentPaths = new HashSet<>();
        Thread.sleep((int) (Math.random() * 50 + 100));
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();
        Elements href = doc.select("a[href]");
        href.forEach(h -> {
            String currentUrl = h.absUrl("href");
            if (checkURL(currentUrl) && addNewURL(currentUrl)) {
                currentPaths.add(currentUrl);
            }
        });

        return currentPaths;
    }

    private synchronized boolean addNewURL(String url){
        return paths.add(url);
    }

    private static boolean checkURL(String currentUrl){
        return currentUrl.startsWith("https://skillbox.ua/") && currentUrl.endsWith("/");
    }
}
