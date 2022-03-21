package response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Mission {
    private int id;
    private String name;
    private LocalDateTime dateCreating;
    private LocalDateTime deadline;
    private boolean isComplete;

    @Override
    public String toString() {
        return "{\n" +
                "\tid = " + id +
                "\n\tname = '" + name + '\'' +
                "\n\tdateCreating = " + dateCreating +
                "\n\tdeadline = " + deadline +
                "\n\tisComplete = " + isComplete +
                "\n}\n";
    }
}
