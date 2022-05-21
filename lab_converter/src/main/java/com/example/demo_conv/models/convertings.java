package com.example.demo_conv.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class convertings implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String metric_name,household_name;
    private double coefficient;
    private int product_id;
    public String getMetric_name(){
        return metric_name;
    }
    public String getHousehold_name(){
        return household_name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getProduct_id() {
        return product_id;
    }
}
