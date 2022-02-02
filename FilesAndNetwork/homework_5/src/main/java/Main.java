import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            Metro metro = parser.parse("https://skillbox-java.github.io/");

            objectMapper.writeValue(new File("src/main/resources/output.json"), metro);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
