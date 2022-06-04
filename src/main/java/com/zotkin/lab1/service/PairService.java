package com.zotkin.lab1.service;

import com.zotkin.lab1.entity.PairEntity;
import com.zotkin.lab1.repository.PairRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairService {

    @Autowired
    private PairRepo pairRepo;

    public PairEntity create(PairEntity pair){
        return pairRepo.save(pair);
    }

    public List<PairEntity> getAll(){
        List<PairEntity> res = new ArrayList<PairEntity>();
        for (PairEntity pair : pairRepo.findAll()) {
            res.add(pair);
        }
        
        return res;
    }

}
