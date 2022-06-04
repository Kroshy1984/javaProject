package ru.sfedu.BiathlonAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfedu.BiathlonAnalyzer.model.Race;
import ru.sfedu.BiathlonAnalyzer.service.RaceService;

import java.util.List;

@Controller
public class RaceController {
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService){
        this.raceService = raceService;
    }

    @GetMapping("/races")
    public String findAll(Model model){
        List<Race> races = raceService.getAll();
        model.addAttribute("races", races);

        return "race-list";
    }

    @GetMapping("/race-create")
    public String createRaceForm(Race race){
        return "race-create";
    }

    @PostMapping("/race-create")
    public String createRace(Race race){
        raceService.saveRace(race);
        return "redirect:/races";
    }

    @GetMapping("race-delete/{id}")
    public String deleteRace(@PathVariable("id") long id){
        raceService.deleteById(id);
        return "redirect:/races";
    }

    @GetMapping("race-update/{id}")
    public String updateRaceForm(@PathVariable("id") Long id, Model model){
        Race race = raceService.getById(id);
        model.addAttribute("race", race);
        return "/race-update";
    }

    @PostMapping("/race-update")
    public String updateRace(Race race){
        raceService.saveRace(race);
        return "redirect:/races";
    }
}
