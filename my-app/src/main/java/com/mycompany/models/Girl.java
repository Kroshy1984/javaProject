package com.mycompany.models;

public class Girl {
    private Long Id;
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    @Override
    public String toString() {
        return "Girl [Id=" + Id + ", Name=" + Name + "]";
    }
    public void setName(String name) {
        Name = name;
    }
    private String Name;    
}
