import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    private static Logger logger;
    private ArrayList<Line> allLine;
    private ArrayList<Station> allStations;
    private ArrayList<Station>[] allConnections;

    public void parse(String url) {
        allLine = new ArrayList<>();
        allStations = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            logger = LogManager.getLogger("fileLogger");

            Elements lines = doc.select("span[data-line]");
            Elements stations = doc.select("div[data-line]");
            Elements singleStations = doc.select("p");
            ArrayList<Elements> allSt = new ArrayList<>();

            stations.forEach(st -> allSt.add(st.select("p")));

            AtomicInteger j = new AtomicInteger(1);
            lines.forEach(e -> {
                int number = j.getAndIncrement();
                Line line = new Line(number, e.text());
                addStation(allSt.get(number - 1), line);
                allLine.add(line);
            });

            ArrayList<Station>[] connection = new ArrayList[singleStations.size()];

            int i = 0;
            for (Element st : singleStations) {
                i++;
                if (!st.select("span[title]").isEmpty()) {
                    String p = st.select("span[title]").toString();
                    String name = st.select("span.name").text();
                    connection[i] = addConnection(p, name, searchLineAboutStation(name, allLine)).getConnection();
                }
            }
            allConnections = connection;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    private Connection addConnection(String p, String name, int number) {
        String[] stations = p.split("\n");
        Connection con = new Connection();

        Station stat1 = new Station(name, getLineAtNumber(number));
        con.addConnect(stat1);

        for(String st : stations) {
            String nameOfStation = getNameAtSt(st);
            int numberOfLine = getNumberAtSt(st);

            Station stat2 = new Station(nameOfStation, getLineAtNumber(numberOfLine, number));
            con.addConnect(stat2);
        }

        return con;
    }

    private Line getLineAtNumber(int number){
        for (Line line : allLine) {
            if (number == line.getNumber()) {
                return line;
            }
        }
        return null;
    }

    private Line getLineAtNumber(int number, int notThisNumber){
        for (Line line : allLine) {
            if (number == line.getNumber() && line.getNumber() != notThisNumber) {
                return line;
            }
        }
        return null;
    }

    private String getNameAtSt(String st) {
        int startName = st.indexOf("«") + 1;
        int endName = st.indexOf("»");
        return st.substring(startName, endName);
    }

    private int getNumberAtSt(String st) {
        int startNumber = st.indexOf("<span class=\"t-icon-metroln ln-") + 31;
        int endNumber = st.indexOf('"', startNumber);
        String strNumber = st.substring(startNumber, endNumber);
        int result;
        switch (strNumber) {
            case "11A" -> result = 12;
            case "12" -> result = 13;
            case "D1" -> result = 16;
            case "D2" -> result = 17;
            default -> result = Integer.parseInt(strNumber);
        }
        return result;
    }

    private static void addStation(Elements stations, Line line) {
        stations.forEach(st -> {
            Station station = new Station(st.child(1).text(), line);
            line.addStation(station);
        });
    }

    private int searchLineAboutStation(String name, ArrayList<Line> allLine) {
        for (Line line : allLine) {
            Station st = new Station(name, line);
            if (line.getStations().contains(st)) {
                return line.getNumber();
            }
        }
        return 0;
    }
}