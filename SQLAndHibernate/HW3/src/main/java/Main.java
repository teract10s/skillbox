import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static List<Student> students;
    private static List<Course> courses;
    public static void main(String[] args) throws SQLException {
        Session session = Configuration.getSession();
        Connection connection = Configuration.getConnection();
        connection.setAutoCommit(false);

        String hql = "From " + Purchaselist.class.getSimpleName();
        String hqlCourses = "From " + Course.class.getSimpleName();
        String hqlStudents = "From " + Student.class.getSimpleName();

        students = session.createQuery(hqlStudents).getResultList();
        courses = session.createQuery(hqlCourses).getResultList();
        List<Purchaselist> purchaselists = session.createQuery(hql).getResultList();

        session.beginTransaction();

        purchaselists.forEach(pl -> {
            LinkedPurchaseList linkedPurchaseList = getLinkedPurchaseList(pl);
            session.save(linkedPurchaseList);
        });
        session.getTransaction().commit();
        Configuration.closeAll();
    }

    private static Key getKey(int studentId, int courseId){
        Key key = new Key();
        key.setStudentId(studentId);
        key.setCourseId(courseId);
        return key;
    }

    private static LinkedPurchaseList getLinkedPurchaseList(Purchaselist purchaselist){
        int studentId = getStudentIdWithName(purchaselist.getStudentName()).getId();
        int courseId = getCourseIdWithName(purchaselist.getCourseName()).getId();
        Key key = getKey(studentId, courseId);
        return new LinkedPurchaseList(key, studentId, courseId);
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
