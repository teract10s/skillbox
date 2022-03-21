package main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Mission;

import java.util.List;

import static main.Main.loggerError;
import static main.Main.loggerInfo;

@RestController
public class ToDoListController {

    @GetMapping("/ToDoList/")
    public List<Mission> getAllMission(){
        loggerInfo.log(Level.INFO, "Get all missions");
        return Storage.getAllMission();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id){
        Mission mission = Storage.getMission(id);
        if (mission == null) {
            loggerError.log(Level.INFO, "Error: 404. Not found mission");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        loggerInfo.log(Level.INFO, "Get one mission");
        return new ResponseEntity(mission, HttpStatus.OK);
    }

    @PostMapping("/ToDoList/")
    public int add(Mission mission){
        return Storage.addMission(mission);
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity editAllLine(@PathVariable int id, Mission mission){
        Mission currentMission = Storage.getMission(id);
        if (currentMission == null) {
            loggerError.log(Level.INFO, "Error: 404. Not found mission");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        currentMission.setName(mission.getName());
        currentMission.setDateCreating(mission.getDateCreating());
        currentMission.setDeadline(mission.getDeadline());
        currentMission.setComplete(mission.isComplete());
        loggerInfo.log(Level.INFO, "Edit all line at mission");
        return new ResponseEntity(currentMission, HttpStatus.OK);
    }

    @PatchMapping("/ToDoList/{id}")
    public ResponseEntity editSomeLine(@PathVariable int id, Mission mission){
        Mission currentMission = Storage.getMission(id);
        if (currentMission == null) {
            loggerError.log(Level.INFO, "Error: 404. Not found mission");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (mission.getName() != null) {
            currentMission.setName(mission.getName());
        }
        if (mission.getDateCreating() != null) {
            currentMission.setDateCreating(mission.getDateCreating());
        }
        if (mission.getDeadline() != null) {
            currentMission.setDeadline(mission.getDeadline());
        }
        currentMission.setComplete(mission.isComplete());
        loggerInfo.log(Level.INFO, "Edit some line at mission");
        return new ResponseEntity(currentMission, HttpStatus.OK);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteMissions(){
        Storage.removeAll();
        loggerInfo.log(Level.INFO, "Delete all missions");
    }

    @DeleteMapping("/ToDoList/{id}")
    public boolean deleteMission(@PathVariable int id){
        return Storage.removeMission(id);
    }
}