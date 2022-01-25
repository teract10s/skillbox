import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static Logger logger;
    private static final ArrayList<Line> allLine = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io/").get();
            logger = LogManager.getLogger("fileLogger");

            Elements lines = doc.select("span[data-line]");
            Elements station = doc.select("div[data-line]");

            station.forEach(System.out::println);

//            connections.forEach(con -> {
//                System.out.println(con.className());
//            });

            String[] strStations = new String[station.size()];
            for (int i = 0; i < station.size(); i++){
                strStations[i] = station.get(i).text();
            }

            AtomicInteger i = new AtomicInteger(1);
            lines.forEach(e ->{
                int number = i.getAndIncrement();
                Line line = new Line(number, e.text());
                addStation(strStations[number - 1], line);
                allLine.add(line);
            });
        }catch (Exception ex){
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    private static void addStation(String stations, Line line){
        String[] str = stations.split(" [0-9]{1,2}\\. ");
        for (int i = 1; i <= str.length; i++){
            if (i == 1){
                Station st = new Station(str[i - 1].substring(3), line);
                line.addStation(st);
                continue;
            }
            Station st = new Station(str[i - 1], line);
            line.addStation(st);
        }
    }
}
