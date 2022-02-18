import org.hibernate.Session;

import java.util.ArrayList;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = Configuration.getSession();

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i <= 45; i++) {
            Course course = session.get(Course.class, i);
            courses.add(course);
        }
        courses.forEach(System.out::println);
        Configuration.closeAll();
    }
}
