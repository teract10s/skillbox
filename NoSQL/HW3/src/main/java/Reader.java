import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    private String file;
    private List<String[]> allRows;
    @Getter
    private List<Student> students;

    public Reader(String file) {
        this.file = file;
        allRows = new ArrayList<>();
        init();
        createListStudents();
    }
    
    private void init(){
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        allRows = null;
        try {
            assert reader != null;
            allRows = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void createListStudents(){
        students = new ArrayList<>();
        for(String[] row : allRows){
            Student student = new Student();
            student.setName(row[0]);
            student.setAge(Integer.parseInt(row[1]));
            student.setCourses(new ArrayList<>(Arrays.asList(row).subList(2, row.length)));
            students.add(student);
        }
    }
}
