import lombok.Getter;
import org.jsoup.Connection;
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
    private final Set<String> paths = new HashSet<>();
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
               if (!str.startsWith(url) || str.length() <= url.length()) {
                   continue;
               }
//               Thread.sleep((int) (Math.random() * 50 + 100));
//               .timeout(10 * 1000)
               CreateListOfLinks task = new CreateListOfLinks(str);
               task.fork();
               taskList.add(task);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CreateListOfLinks task : taskList){
            paths.addAll(task.join());
        }

        return paths;
    }

    private static Set<String> getCurrentPaths(String url) throws IOException{
        Set<String> currentPaths = new HashSet<>();
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();

        int code = Jsoup.connect(url).execute().statusCode();

//        System.out.println("\n----------------\n" + code + "\n----------------\n");
//        if (code != 200){
//            return new HashSet<>();
//        }

        Elements href = doc.select("a[href]");
        href.forEach(h -> {
            String result = getOnlyHref(h.toString(), url);
            if (!result.isEmpty() && result.startsWith(url)) {
                currentPaths.add(result);
                System.out.println(result);
            }
        });

        return currentPaths;
    }

    private static String getOnlyHref(String str, String url){
        int start = str.indexOf("href=\"") + 6;
        int end = str.indexOf("\"", start);

        String result = str.substring(start, end);
        result = result.charAt(0) == '/' ? url + result.substring(1) : result;

        try {
            int code = Jsoup.connect(result).execute().statusCode();
            System.out.println("\n----------------\n" + "code :\t" + code + "\n----------------\n");
            if (code != 200){
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Set<String> getLinksFromPage(String url) {
//        Set<String> linksSetFromPage = new HashSet<>();
//        try {
//            Thread.sleep((int) (Math.random() * (150 - 100) + 100));
//            Document parseSite = Jsoup.connect(url).userAgent(getUserAgent()).timeout(10 * 1000) .get();
//            Elements links = parseSite.select("a");
//            LOGGER.info(LINK_PARSING, "Успешный парсинг ссылки: {}", url);
//            linksSetFromPage = validateLink(links);
//        } catch (Exception e) {
//            LOGGER.error(e);
//        }
//        return linksSetFromPage;
//    }
}
