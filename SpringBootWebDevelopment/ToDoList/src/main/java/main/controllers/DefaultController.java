package main.controllers;

import main.model.Mission;
import main.service.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DefaultController {
    private final ToDoListService service;

    public DefaultController(ToDoListService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index(Model model){
        List<Mission> missionList = service.getAllMission();
        model.addAttribute("missions", missionList);
        model.addAttribute("missionCount", missionList.size());
        return "index";
    }
}
