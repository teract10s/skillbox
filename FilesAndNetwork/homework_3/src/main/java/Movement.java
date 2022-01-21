import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
public class Movement {
    private String typeOfCheck;
    private String numberOfCheck;
    private String currency;
    private Date date;
    private String wiringReference;
    private String operationDescription;
    private double coming;
    private double consumption;

    public String toString(){
        return operationDescription;
    }
}


