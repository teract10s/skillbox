import lombok.Data;

@Data
public class Connect {
    private String number;
    private String name;

    public Connect(String number, String name) {
        this.number = number;
        this.name = name;
    }
}
