import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    private static Logger logger;
    private ArrayList<Line> allLine;

    public Metro parse(String url) {
        Metro result = new Metro();
        allLine = new ArrayList<>();
        ArrayList<List<Connect>> allConnections = new ArrayList<>();

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
                String strNumber = getNumberStr(number);
                Line line = new Line(strNumber, e.text());
                addStation(allSt.get(number - 1), line);
                allLine.add(line);
            });



            for (Element st : singleStations) {
                if (!st.select("span[title]").isEmpty()) {
                    String p = st.select("span[title]").toString();
                    String name = st.select("span.name").text();
                    List<Connect> con = (addConnection(p, name, searchLineAboutStation(name, allLine)));
                    allConnections.add(con);
                }
            }

            result.setAllLine(allLine);
            result.setAllConnections(allConnections);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
            return result;
        }
        return result;
    }

    private String getNumberStr(int number){
        String result = null;

        if (number < 12){
            result = String.valueOf(number);
        }
        switch (number){
            case 12 -> result = "11A";
            case 13 -> result = "12";
            case 14 -> result = "14";
            case 15 -> result = "15";
            case 16 -> result = "D1";
            case 17 -> result = "D2";
        }
        return result;
    }

    private List<Connect> addConnection(String p, String name, String number) {
        String[] stations = p.split("\n");
        List<Connect> con = new ArrayList<>();

        con.add(new Connect(number, name));

        for(String st : stations) {
            String nameOfStation = getNameAtSt(st);
            String numberOfLine = getNumberAtSt(st);

            Connect connect = new Connect(numberOfLine, nameOfStation);
            con.add(connect);
        }
        return con;
    }

    private String getNameAtSt(String st) {
        int startName = st.indexOf("«") + 1;
        int endName = st.indexOf("»");
        return st.substring(startName, endName);
    }

    private String getNumberAtSt(String st) {
        int startNumber = st.indexOf("<span class=\"t-icon-metroln ln-") + 31;
        int endNumber = st.indexOf('"', startNumber);
        return st.substring(startNumber, endNumber);
    }


    private static void addStation(Elements stations, Line line) {
        stations.forEach(st -> {
            Station station = new Station(st.child(1).text(), line);
            line.addStation(station);
        });
    }

    private String searchLineAboutStation(String name, ArrayList<Line> allLine) {
        for (Line line : allLine) {
            Station st = new Station(name, line);
            if (line.getStations().contains(st)) {
                return line.getNumber();
            }
        }
        return null;
    }
}