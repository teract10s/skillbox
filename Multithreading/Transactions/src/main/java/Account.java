import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private long money;
    private String accNumber;
    private boolean isLocked;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.isLocked = false;
    }
}
