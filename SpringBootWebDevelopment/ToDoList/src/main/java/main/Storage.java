package main;

import main.essence.Mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer, Mission> missions = new HashMap<>();

    public static List<Mission> getAllMission (){
        ArrayList<Mission> missionsList = new ArrayList<>();
        missionsList.addAll(missions.values());
        return missionsList;
    }

    public static int addMission (Mission mission){
        int id = currentId++;
        mission.setId(id);
        missions.put(id, mission);
        return id;
    }

    public static Mission getMission (int missionId){
        if (missions.containsKey(missionId)) {
            return missions.get(missionId);
        }
        return null;
    }

    public static boolean removeMission (int missionId){
        if (missions.containsKey(missionId)){
            missions.remove(missionId);
            return true;
        }
        return false;
    }

    public static void removeAll(){
        missions = new HashMap<>();
    }
}
