import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metro {
    private ArrayList<Station> allStations;
    private ArrayList<Line> allLine;
    private ArrayList<List<Station>> allConnections;

    public Metro() {
    }

    @Override
    public String toString() {
        return "Metro{" +
                "allStations=" + allStations +
                "allLine=" + allLine +
                ", allConnections=" + allConnections +
                '}';
    }
}
