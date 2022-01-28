import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parse("https://skillbox-java.github.io/");

        Metro metro = new Metro(parser);

        String json = GSON.toJson(metro);
        System.out.println(json);
    }
}
