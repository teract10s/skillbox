import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParseFromFile {
    private static Logger logger;
    private static Metro metro;

    public ParseFromFile(Path path) {
        metro = new Metro();
        logger = LogManager.getLogger("fileLogger");
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(path));

            JSONArray linesArray = (JSONArray) jsonData.get("allLine");
            parseLines(linesArray);

            JSONArray connectionsArray = (JSONArray) jsonData.get("allConnections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Metro getMetro(){
        return metro;
    }

    private void parseConnections(JSONArray connectionsArray) {
        try {
            ArrayList<List<Connect>> allConnections = new ArrayList<>();

            connectionsArray.forEach(connectionObject ->
            {
                ArrayList<Connect> connects = new ArrayList<>();
                JSONArray connection = (JSONArray) connectionObject;
                connection.forEach(item -> {
                    JSONObject itemObject = (JSONObject) item;
                    connects.add(new Connect(
                            (String) itemObject.get("number"),
                            (String) itemObject.get("name"))
                    );
                });
                allConnections.add(connects);
            });
            metro.setAllConnections(allConnections);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage());
        }
    }

    private void parseLines(JSONArray linesArray) {
        try {
            ArrayList<Line> allLine = new ArrayList<>();

            linesArray.forEach(lineObject -> {
                JSONObject lineJsonObject = (JSONObject) lineObject;
                Line line = new Line(
                        (String) lineJsonObject.get("number"),
                        (String) lineJsonObject.get("name")
                );
                JSONArray stationsArray = (JSONArray) lineJsonObject.get("stations");

                stationsArray.forEach(stationObject -> {
                    String name = (String) ((JSONObject) stationObject).get("stations");
                    line.addStation(new Station(name, line));
                });
                allLine.add(line);
            });

            metro.setAllLine(allLine);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage());
        }
    }

    private static String getJsonFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(builder::append);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage());
        }
        return builder.toString();
    }

}
