import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class KeyAtName implements Serializable {
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;
}
