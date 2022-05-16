package com.zotkin.lab1.model;

import com.zotkin.lab1.entity.UserEntity;

public class User {
    private Long id;
    private String name;
    private String lastname;
    private int age;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLastname(entity.getLastname());
        model.setAge(entity.getAge());
        return model;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
