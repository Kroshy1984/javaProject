package com.zotkin.lab1.controller;


import com.zotkin.lab1.entity.PairEntity;
import com.zotkin.lab1.service.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pair")
public class PairController {

    @Autowired
    private PairService pairService;

    @GetMapping()
    public ResponseEntity getAllPairs(){
        try {
            return ResponseEntity.ok(pairService.getAll());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody PairEntity pair){
        try {

            pairService.create(pair);
            return ResponseEntity.ok(pair);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }



}
