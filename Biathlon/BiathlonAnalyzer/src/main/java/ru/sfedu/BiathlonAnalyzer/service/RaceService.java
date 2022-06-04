package ru.sfedu.BiathlonAnalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.BiathlonAnalyzer.model.Race;
import ru.sfedu.BiathlonAnalyzer.repository.RaceRepository;

import java.util.List;

@Service
public class RaceService {
    @Autowired
    private RaceRepository raceRepository;

    public Race getById(long id){
        return raceRepository.getById(id);
    }

    public List<Race> getAll(){
        return raceRepository.findAll();
    }

    public Race saveRace(Race race){
        return raceRepository.save(race);
    }

    public void deleteById(long id){
        raceRepository.deleteById(id);
    }
}
