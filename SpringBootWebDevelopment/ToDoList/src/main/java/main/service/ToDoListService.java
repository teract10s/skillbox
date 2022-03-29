package main.service;

import main.model.Mission;
import main.model.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    private ToDoListRepository repository;

    public ToDoListService(ToDoListRepository repository) {
        this.repository = repository;
    }

    public List<Mission> getAllMission(){
        Iterable<Mission> missionIterable = repository.findAll();
        ArrayList<Mission> missions = new ArrayList<>();
        for (Mission mission : missionIterable){
            missions.add(mission);
        }
        return missions;
    }

    public Mission getMission(int id){
        Optional<Mission> optionalMission = repository.findById(id);
        return optionalMission.get();
    }

    public Mission addMission(Mission mission){
        Mission newMission = repository.save(mission);
        return newMission;
    }

    public Mission editAllLineAtMission(int id, Mission mission){
        Optional<Mission> optionalMission = repository.findById(id);
        if (!optionalMission.isPresent()){
            return null;
        }
        Mission currentMission = optionalMission.get();
        currentMission.setName(mission.getName());
        currentMission.setDateCreating(mission.getDateCreating());
        currentMission.setDeadline(mission.getDeadline());
        currentMission.setComplete(mission.isComplete());
        return repository.save(currentMission);
    }

    public Mission editSomeLineAtMission(int id, Mission mission){
        Optional<Mission> optionalMission = repository.findById(id);
        if (!optionalMission.isPresent()){
            return null;
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
        return repository.save(currentMission);
    }

    public void deleteAllMissions(){
        repository.deleteAll();
    }

    public void deleteOneMission(int id){
        repository.deleteById(id);
    }
}
