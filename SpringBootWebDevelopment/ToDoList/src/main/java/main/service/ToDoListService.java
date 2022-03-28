package main;

import main.model.Mission;
import main.model.ToDoListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    private ToDoListRepository toDoListRepository;

    public List<Mission> getAllMission(){
        Iterable<Mission> missionIterable = toDoListRepository.findAll();
        ArrayList<Mission> missions = new ArrayList<>();
        for (Mission mission : missionIterable){
            missions.add(mission);
        }
        return missions;
    }

    public ResponseEntity<Mission> getMission(int id){
        Optional<Mission> optionalMission = toDoListRepository.findById(id);
        if (!optionalMission.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<Mission>(optionalMission.get(), HttpStatus.OK);
    }

    public int addMission(Mission mission){
        Mission newMission = toDoListRepository.save(mission);
        return newMission.getId();
    }

    public ResponseEntity<Mission> editAllLineAtMission(int id, Mission mission){
        Optional<Mission> optionalMission = toDoListRepository.findById(id);
        if (!optionalMission.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Mission currentMission = optionalMission.get();
        currentMission.setName(mission.getName());
        currentMission.setDateCreating(mission.getDateCreating());
        currentMission.setDeadline(mission.getDeadline());
        currentMission.setComplete(mission.isComplete());
        return new ResponseEntity<Mission>(toDoListRepository.save(currentMission), HttpStatus.OK);
    }

    public ResponseEntity<Mission> editSomeLineAtMission(int id, Mission mission){
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
        return new ResponseEntity<Mission>(toDoListRepository.save(currentMission), HttpStatus.OK);
    }

    public void deleteAllMissions(){
        toDoListRepository.deleteAll();
    }

    public void deleteOneMission(int id){
        toDoListRepository.deleteById(id);
    }
}
