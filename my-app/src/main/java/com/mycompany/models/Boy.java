package com.mycompany.models;

public class Boy {
    private Long Id;
    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "Boy [Id=" + Id + ", Name=" + Name + "]";
    }

   
}
