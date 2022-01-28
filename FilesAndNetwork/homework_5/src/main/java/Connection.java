import java.util.ArrayList;

public class Connection {
    ArrayList<Station> connection;

    public Connection() {
        connection = new ArrayList<>();
    }

    public void addConnect(Station station){
        connection.add(station);
    }

    public ArrayList<Station> getConnection() {
        return connection;
    }
}
