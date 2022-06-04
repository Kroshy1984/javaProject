package ru.sfedu.BiathlonAnalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfedu.BiathlonAnalyzer.model.Result;
import ru.sfedu.BiathlonAnalyzer.model.Sportsman;
import ru.sfedu.BiathlonAnalyzer.service.ResultService;
import ru.sfedu.BiathlonAnalyzer.service.SportsmanService;

import java.util.List;

@Controller
public class ResultController {
    private final ResultService resultService;
    private final SportsmanService sportsmanService;

    @Autowired
    public ResultController(ResultService resultService, SportsmanService sportsmanService) {
        this.resultService = resultService;
        this.sportsmanService = sportsmanService;
    }

    @GetMapping("/results")
    public String findAll(Model model) {
        List<Result> results = resultService.getAll();
        model.addAttribute("results", results);

        return "result-list";
    }

    @GetMapping("/result-create")
    public String createResultForm(Result result) {
        return "result-create";
    }

    @PostMapping("/result-create")
    public String createResult(Result result) {
        resultService.saveResult(result);
        return "redirect:/results";
    }

    @GetMapping("result-delete/{id}")
    public String deleteResult(@PathVariable("id") long id) {
        resultService.deleteById(id);
        return "redirect:/results";
    }

    @GetMapping("result-update/{id}")
    public String updateResultForm(@PathVariable("id") Long id, Model model) {
        Result result = resultService.getById(id);
        model.addAttribute("result", result);
        return "/result-update";
    }

    @PostMapping("/result-update")
    public String updateResult(Result result) {
        resultService.saveResult(result);
        return "redirect:/results";
    }


    @GetMapping("/analyzer-analyze/{id}")
    public String calcRes(@PathVariable("id") Long id, Model model){
        float gap = resultService.calculateGap(id);
        model.addAttribute("gap", gap);
        model.addAttribute("sportsman", sportsmanService.getById(id));
        return "analyzer-analyze";
    }
}
