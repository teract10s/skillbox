import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Main {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            Metro metro = parser.parse("https://skillbox-java.github.io/");
            String stringMetro = objectMapper.writeValueAsString(metro);
            Files.write(Paths.get("src/main/resources/metro.json"), stringMetro.getBytes(), StandardOpenOption.CREATE);


            ParseFromFile parseFromFile = new ParseFromFile(Paths.get("src/main/resources/metro.json"));
            System.out.println(getCountStationsAtLine(parseFromFile.getMetro()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getCountStationsAtLine(Metro m){
        StringBuilder builder = new StringBuilder();
        ArrayList<Line> allLine = m.getAllLine();

        allLine.forEach(line -> {
            String s = line.getNumber() + "\t" + line.getStations().size() + "\n";
            builder.append(s);
        });
        return builder.toString();
    }
}
