package ru.sfedu.BiathlonAnalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.BiathlonAnalyzer.model.Race;
import ru.sfedu.BiathlonAnalyzer.model.Sportsman;
import ru.sfedu.BiathlonAnalyzer.repository.RaceRepository;
import ru.sfedu.BiathlonAnalyzer.repository.SportsmanRepository;

import java.util.List;

@Service
public class SportsmanService {
    @Autowired
    private SportsmanRepository sportsmanRepository;

    public Sportsman getById(long id){
        return sportsmanRepository.getById(id);
    }

    public List<Sportsman> getAll(){
        return sportsmanRepository.findAll();
    }

    public Sportsman saveSportsman(Sportsman sportsman){
        return sportsmanRepository.save(sportsman);
    }

    public void deleteById(long id){
        sportsmanRepository.deleteById(id);
    }
}
