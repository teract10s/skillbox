package main.controllers;

import main.service.ToDoListService;
import main.model.Mission;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist/")
public class ToDoListController {
    private final ToDoListService service;

    public ToDoListController(ToDoListService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Mission> getAllMission(){
        return service.getAllMission();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> get(@PathVariable int id){
        if (service.getMission(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.getMission(id), HttpStatus.OK);
    }
    
    @PostMapping("/")
    public Mission add(@RequestBody Mission mission){
        return service.addMission(mission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mission> editAllLine(@PathVariable int id, Mission mission){
        if (service.editAllLineAtMission(id, mission) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.editAllLineAtMission(id, mission), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Mission> editSomeLine(@PathVariable int id, Mission mission){
        if (service.editSomeLineAtMission(id, mission) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(service.editSomeLineAtMission(id, mission), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Mission> deleteMissions(){
        service.deleteAllMissions();
        return new ResponseEntity<Mission>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mission> deleteMission(@PathVariable int id) {
        if (service.deleteOneMission(id)) {
            return new ResponseEntity<Mission>(HttpStatus.OK);
        }
        return new ResponseEntity<Mission>(HttpStatus.NOT_FOUND);
    }
}