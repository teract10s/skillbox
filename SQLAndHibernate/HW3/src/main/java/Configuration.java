import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Configuration {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();

        return session;
    }

    public static Statement getStatement() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "trs.20041711";

        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();
        return statement;
    }

    public static void closeAll(){
        session.close();
        sessionFactory.close();
    }
}
