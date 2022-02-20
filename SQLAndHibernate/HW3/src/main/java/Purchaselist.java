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
@Table(name = "Purchaselist")
public class Purchaselist {
    @EmbeddedId
    private KeyAtName id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    @Override
    public String toString() {
        return "Purchaselist{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
