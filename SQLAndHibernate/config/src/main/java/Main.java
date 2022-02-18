import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "trs.20041711";
        StringBuilder result = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, COUNT(*)/(MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date))) FROM PurchaseList pl GROUP BY pl.course_name;");

            while (resultSet.next()) {
                String str = resultSet.getString(1) + " - " + resultSet.getString(2) + "\n";
                result.append(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(result);
    }
}