import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = Configuration.getSession();

        Configuration.closeAll();
    }
}
