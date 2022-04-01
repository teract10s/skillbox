package main.controllers;

import main.service.ToDoListService;
import main.model.Mission;

import org.springframework.http.HttpStatus;
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

    @GetMapping("/todolist/{id}")
    public ResponseEntity<Mission> get(@PathVariable int id){
        if (service.getMission(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.getMission(id), HttpStatus.OK);
    }
    
    @PostMapping("/todolist/")
    public Mission add(@RequestBody Mission mission){
        return service.addMission(mission);
    }

    @PutMapping("/todolist/{id}")
    public ResponseEntity<Mission> editAllLine(@PathVariable int id, Mission mission){
        if (service.editAllLineAtMission(id, mission) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.editAllLineAtMission(id, mission), HttpStatus.OK);
    }

    @PatchMapping("/todolist/{id}")
    public ResponseEntity<Mission> editSomeLine(@PathVariable int id, Mission mission){
        if (service.editSomeLineAtMission(id, mission) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.editSomeLineAtMission(id, mission), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/")
    public void deleteMissions(){
        service.deleteAllMissions();
    }

    @DeleteMapping("/todolist/{id}")
    public void deleteMission(@PathVariable int id){
        service.deleteOneMission(id);
    }
    
}