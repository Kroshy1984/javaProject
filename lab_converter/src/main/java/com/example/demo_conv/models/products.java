package com.example.demo_conv.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class products implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String name;

    public String getName(){
        return name;
    }
}
