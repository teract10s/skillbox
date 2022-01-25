import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    private static Logger logger;

    public void parse(String url) {
        ArrayList<Line> allLine = getAllLine();
        try {
            Document doc = Jsoup.connect(url).get();
            logger = LogManager.getLogger("fileLogger");

            Elements lines = doc.select("span[data-line]");
            Elements stations = doc.select("div[data-line]");

            ArrayList<Elements> allStations = new ArrayList<>();

            stations.forEach(st -> allStations.add(st.select("p")));

            AtomicInteger i = new AtomicInteger(1);
            lines.forEach(e -> {
                int number = i.getAndIncrement();
                Line line = new Line(number, e.text());
                addStation(allStations.get(number - 1), line);
                allLine.add(line);
            });

            allLine.forEach(System.out::println);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    private static void addStation(Elements stations, Line line){
        stations.forEach(st ->{
            Station station = new Station(st.child(1).text(), line);
            line.addStation(station);
        });
    }

    private ArrayList<Line> getAllLine(){
        return new ArrayList<>();
    }
}
