import org.hibernate.Session;

import javax.swing.plaf.nimbus.State;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    private static List<Student> students;
    private static List<Course> courses;
    public static void main(String[] args) throws SQLException {
        Session session = Configuration.getSession();
        Statement statement = Configuration.getStatement();

        String hql = "From " + Purchaselist.class.getSimpleName();
        String hqlCourses = "From " + Course.class.getSimpleName();
        String hqlStudents = "From " + Student.class.getSimpleName();

        students = session.createQuery(hqlStudents).getResultList();
        courses = session.createQuery(hqlCourses).getResultList();

        List<Purchaselist> purchaselists = session.createQuery(hql).getResultList();

        purchaselists.forEach(pl -> {
            Student student = getStudentIdWithName(pl.getStudentName());
            Course course = getCourseIdWithName(pl.getCourseName());
            try {
                statement.executeUpdate("INSERT " + LinkedPurchaseList.class.getSimpleName() +
                        " (student_id, course_id) " +
                        " VALUES (" + getStudentIdWithName(pl.getStudentName()).getId() + ", " +
                        getCourseIdWithName(pl.getCourseName()).getId() + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        purchaselists.forEach(System.out::println);
        Configuration.closeAll();
    }

    private static Student getStudentIdWithName(String name){
        var ref = new Object() {
            Student student = new Student();
        };
        students.forEach(st -> {
            if (st.getName().equals(name)){
                ref.student = st;
            }
        });
        return ref.student;
    }

    private static Course getCourseIdWithName(String name){
        var ref = new Object() {
            Course course = new Course();
        };
        courses.forEach(c -> {
            if (c.getName().equals(name)){
                ref.course = c;
            }
        });
        return ref.course;
    }
}
