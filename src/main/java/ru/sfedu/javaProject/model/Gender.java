package ru.sfedu.javaProject.model;

import java.util.Random;

public enum Gender {
    MALE,
    FEMALE;
    public static Gender getRandomGender(){
        //Random int from 0 to 1
        Random random = new Random();
        if(random.nextBoolean()){
            return MALE;
        }
        return FEMALE;
    }
}
