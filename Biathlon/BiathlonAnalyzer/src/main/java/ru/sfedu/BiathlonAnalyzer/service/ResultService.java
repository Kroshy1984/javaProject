package ru.sfedu.BiathlonAnalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.BiathlonAnalyzer.model.Race;
import ru.sfedu.BiathlonAnalyzer.model.Result;
import ru.sfedu.BiathlonAnalyzer.model.Sportsman;
import ru.sfedu.BiathlonAnalyzer.repository.RaceRepository;
import ru.sfedu.BiathlonAnalyzer.repository.ResultRepository;
import ru.sfedu.BiathlonAnalyzer.repository.SportsmanRepository;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private RaceRepository raceRepository;

    public Result getById(long id){
        return resultRepository.getById(id);
    }

    public List<Result> getAll(){
        return resultRepository.findAll();
    }

    public Result saveResult(Result result){
        return resultRepository.save(result);
    }

    public void deleteById(long id){
        resultRepository.deleteById(id);
    }

    public float calculateGap(long id){
        float athleteTime = 0, winnerTime = 0, gap = 0;
        int k = 0;
        Result result = resultRepository.getById(id);
        List<Race> raceList = raceRepository.findAll();
        List<Result> resultList = resultRepository.findAll();

        for(Race x: raceList){
            for(Result y: resultList){
                if(x.getId() == y.getRace().getId()){
                    if(y.getSportsman().getId() == id){
                        athleteTime = y.getTime();
                    }
                    if(y.getPlace() == 1){
                        winnerTime = y.getTime();
                    }
                }
            }
            gap += (athleteTime - winnerTime);
            k++;
        }
        return gap/k;
    }
}
