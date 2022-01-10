import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        Collections.sort(staff, (o1, o2) -> {
            if (o1.getSalary().compareTo(o2.getSalary()) == 0){
                return o1.toString().compareTo(o2.toString());
            }else{
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
    }
}