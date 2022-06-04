package ru.sfedu.BiathlonAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfedu.BiathlonAnalyzer.model.Race;
import ru.sfedu.BiathlonAnalyzer.model.Sportsman;
import ru.sfedu.BiathlonAnalyzer.service.SportsmanService;

import java.util.List;

@Controller
public class SportsmanController {
    private final SportsmanService sportsmanService;

    @Autowired
    public SportsmanController(SportsmanService sportsmanService){
        this.sportsmanService = sportsmanService;
    }

    @GetMapping("/sportsmen")
    public String findAll(Model model){
        List<Sportsman> sportsmen = sportsmanService.getAll();
        model.addAttribute("sportsmen", sportsmen);

        return "sportsman-list";
    }

    @GetMapping("/sportsman-create")
    public String createSportsmanForm(Sportsman sportsman){
        return "sportsman-create";
    }

    @PostMapping("/sportsman-create")
    public String createSportsman(Sportsman sportsman){
        sportsmanService.saveSportsman(sportsman);
        return "redirect:/sportsmen";
    }

    @GetMapping("sportsman-delete/{id}")
    public String deleteSportsman(@PathVariable("id") long id){
        sportsmanService.deleteById(id);
        return "redirect:/sportsmen";
    }

    @GetMapping("sportsman-update/{id}")
    public String updateSportsmanForm(@PathVariable("id") Long id, Model model){
        Sportsman sportsman = sportsmanService.getById(id);
        model.addAttribute("sportsman", sportsman);
        return "/sportsman-update";
    }

    @PostMapping("/sportsman-update")
    public String updateSportsman(Sportsman sportsman){
        sportsmanService.saveSportsman(sportsman);
        return "redirect:/sportsmen";
    }

    @GetMapping("/analyzer")
    public String biathlonAnalyzer(Model model){
        List<Sportsman> sportsmen = sportsmanService.getAll();
        model.addAttribute("sportsmen", sportsmen);
        return "analyzer-start";
    }
}
