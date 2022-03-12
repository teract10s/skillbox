import lombok.Getter;
import java.util.Scanner;

@Getter
public class Menu {
    private final String url;
    private final String filePath;

    public Menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter url at you site: ");
        url = scanner.nextLine();
        System.out.println("Enter path to the file where you want to save the data: ");
        filePath = scanner.nextLine();
    }
}
