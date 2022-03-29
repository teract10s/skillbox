package main.controllers;

import main.service.ToDoListService;
import main.model.Mission;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoListController {
    private final ToDoListService service;

    public ToDoListController(ToDoListService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Mission> getAllMission(){
        return service.getAllMission();
    }

    @GetMapping("/ToDoList/{id}")
    public ResponseEntity<Mission> get(@PathVariable int id){
        return service.getMission(id);
    }

    @RequestMapping
    @PostMapping("/ToDoList/")
    public int add(Mission mission){
        return service.addMission(mission);
    }

    @PutMapping("/ToDoList/{id}")
    public ResponseEntity<Mission> editAllLine(@PathVariable int id, Mission mission){
        return service.editAllLineAtMission(id, mission);
    }

    @PatchMapping("/ToDoList/{id}")
    public ResponseEntity<Mission> editSomeLine(@PathVariable int id, Mission mission){
        return service.editSomeLineAtMission(id, mission);
    }

    @DeleteMapping("/ToDoList/")
    public void deleteMissions(){
        service.deleteAllMissions();
    }

    @DeleteMapping("/ToDoList/{id}")
    public void deleteMission(@PathVariable int id){
        service.deleteOneMission(id);
    }
}