import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
    private static Logger logger;
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io/").get();
            logger = LogManager.getLogger("fileLogger");

        }catch (Exception ex){
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
}
