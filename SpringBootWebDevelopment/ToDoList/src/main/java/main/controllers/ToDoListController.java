package main.controllers;

import main.model.Mission;

import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @GetMapping("/")
    public List<Mission> list(){
        Iterable<Mission> missionIterable = toDoListRepository.findAll();
        ArrayList<Mission> missions = new ArrayList<>();
        for (Mission mission : missionIterable){
            missions.add(mission);
        }
        return missions;
    }

    @GetMapping("/ToDoList/")
    public List<Mission> getAllMission(){
        Iterable<Mission> missionIterable = toDoListRepository.findAll();
        ArrayList<Mission> missions = new ArrayList<>();
        for (Mission mission : missionIterable){
            missions.add(mission);
        }
        return missions;
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Mission> optionalMission = toDoListRepository.findById(id);
        if (!optionalMission.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalMission.get(), HttpStatus.OK);
    }

    @PostMapping("/ToDoList/")
    public int add(Mission mission){
        Mission newMission = toDoListRepository.save(mission);
        return newMission.getId();
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity editAllLine(@PathVariable int id, Mission mission){
        Optional<Mission> optionalMission = toDoListRepository.findById(id);
        if (!optionalMission.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Mission currentMission = optionalMission.get();
        currentMission.setName(mission.getName());
        currentMission.setDateCreating(mission.getDateCreating());
        currentMission.setDeadline(mission.getDeadline());
        currentMission.setComplete(mission.isComplete());
        return new ResponseEntity(toDoListRepository.save(currentMission), HttpStatus.OK);
    }

    @PatchMapping("/ToDoList/{id}")
    public ResponseEntity editSomeLine(@PathVariable int id, Mission mission){
        Optional<Mission> optionalMission = toDoListRepository.findById(id);
        if (!optionalMission.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Mission currentMission = optionalMission.get();

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
        return new ResponseEntity(toDoListRepository.save(currentMission), HttpStatus.OK);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteMissions(){
        toDoListRepository.deleteAll();
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteMission(@PathVariable int id){
        toDoListRepository.deleteById(id);
    }
}