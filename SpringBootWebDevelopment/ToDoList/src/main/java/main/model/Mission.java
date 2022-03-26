package main.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
