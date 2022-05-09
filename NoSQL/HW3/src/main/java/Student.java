import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Student {
    private String name;
    private int age;
    private List<String> courses;

    public Student() {
    }

    public Student(String name, int age, List<String> courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\n\tname = " + name +
                "\n\tage = " + age +
                "\n\tcourses = " + courses +
                "\n}\n";
    }
}
