import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "trs.20041711";
        StringBuilder result = new StringBuilder();
        ArrayList<String> courseNames = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet namesSet = statement.executeQuery("SELECT * FROM Courses");

            while (namesSet.next()) {
                String courseName = namesSet.getString("name");
                courseNames.add(courseName);
            }

            namesSet.close();
            for (String name : courseNames) {
                int count = 0;
                int min = 13;
                int max = -1;

                ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, pl.subscription_date FROM PurchaseList pl WHERE pl.course_name = \"" + name + "\" ORDER BY pl.subscription_date;");

                while (resultSet.next()) {
                    count++;
                    if (Integer.parseInt(resultSet.getString(2).substring(5, 7)) > max) {
                        max = Integer.parseInt(resultSet.getString(2).substring(5, 7));
                    }
                    if (Integer.parseInt(resultSet.getString(2).substring(5, 7)) < min) {
                        min = Integer.parseInt(resultSet.getString(2).substring(5, 7));
                    }
                }

                double answer = (double) count / (max - min);
                String str = name + " - " + answer + "\n";
                result.append(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(result);
    }
}