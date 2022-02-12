import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    @Column(name = "students_count")
    private int studentsCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions", joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    @Override
    public String toString() {
        return "Course{" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tduration=" + duration +
                ", \n\ttype=" + type +
                ", \n\tdescription='" + description + '\'' +
                ", \n\tteacher=" + teacher +
                ", \n\tstudentsCount=" + studentsCount +
                ", \n\tstudents=" + students +
                ", \n\tprice=" + price +
                ", \n\tpricePerHour=" + pricePerHour +
                "\n}";
    }
}
