import junit.framework.TestCase;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Основные")
public class MainTest extends TestCase {
    ParseFromFile parseFromFile = new ParseFromFile(Paths.get("src/main/resources/metro.json"));
    Metro metro = parseFromFile.getMetro();

    @Test
    @DisplayName("Количество линий")
    public void testCountOfLine() {
        assertEquals(17, metro.getAllLine().size());
    }
}