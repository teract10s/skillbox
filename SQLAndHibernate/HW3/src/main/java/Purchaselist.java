import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "purchaselist")
public class Purchaselist {

    @EmbeddedId
    private KeyAtName id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
<<<<<<< HEAD

    @Column(name = "course_id", nullable = true)
    private Integer courseId;
=======
>>>>>>> 24c0c6b6b9168f1751eede3a5fac39b6b5fe1443
}
