import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Количество станций на линиях")
public class StationTest {
    ParseFromFile parseFromFile = new ParseFromFile(Paths.get("src/main/resources/metro.json"));
    Metro metro = parseFromFile.getMetro();

    @Test
    @DisplayName("Количество станции на 1 линии")
    public void testCountOfStationAt1Line(){
        assertEquals(26, metro.getAllLine().get(0).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 2 линии")
    public void testCountOfStationAt2Line(){
        assertEquals(24, metro.getAllLine().get(1).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 3 линии")
    public void testCountOfStationAt3Line(){
        assertEquals(22, metro.getAllLine().get(2).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 4 линии")
    public void testCountOfStationAt4Line(){
        assertEquals(13, metro.getAllLine().get(3).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 5 линии")
    public void testCountOfStationAt5Line(){
        assertEquals(12, metro.getAllLine().get(4).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 6 линии")
    public void testCountOfStationAt6Line(){
        assertEquals(24, metro.getAllLine().get(5).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 7 линии")
    public void testCountOfStationAt7Line(){
        assertEquals(23, metro.getAllLine().get(6).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 8 линии")
    public void testCountOfStationAt8Line(){
        assertEquals(20, metro.getAllLine().get(7).getStations().size());
    }



    @Test
    @DisplayName("Количество станции на 9 линии")
    public void testCountOfStationAt9Line(){
        assertEquals(25, metro.getAllLine().get(8).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 10 линии")
    public void testCountOfStationAt10Line(){
        assertEquals(23, metro.getAllLine().get(9).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 11 линии")
    public void testCountOfStationAt11Line(){
        assertEquals(6, metro.getAllLine().get(10).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 12 линии")
    public void testCountOfStationAt12Line(){
        assertEquals(3, metro.getAllLine().get(11).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 13 линии")
    public void testCountOfStationAt13Line(){
        assertEquals(7, metro.getAllLine().get(12).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 14 линии")
    public void testCountOfStationAt14Line(){
        assertEquals(31, metro.getAllLine().get(13).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 15 линии")
    public void testCountOfStationAt15Line(){
        assertEquals(11, metro.getAllLine().get(14).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 16 линии")
    public void testCountOfStationAt16Line(){
        assertEquals(25, metro.getAllLine().get(15).getStations().size());
    }

    @Test
    @DisplayName("Количество станции на 17 линии")
    public void testCountOfStationAt17Line(){
        assertEquals(35, metro.getAllLine().get(16).getStations().size());
    }
}
