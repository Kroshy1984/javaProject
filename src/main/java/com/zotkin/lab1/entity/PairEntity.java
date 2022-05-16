package com.zotkin.lab1.entity;

import javax.persistence.*;

@Entity
public class PairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="first_user", referencedColumnName="ID")
    private UserEntity first_user;

    @ManyToOne
    @JoinColumn(name="second_user", referencedColumnName="ID")
    private UserEntity second_user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getFirst_user() {
        return first_user;
    }

    public void setFirst_user(UserEntity first_user) {
        this.first_user = first_user;
    }

    public UserEntity getSecond_user() {
        return second_user;
    }

    public void setSecond_user(UserEntity second_user) {
        this.second_user = second_user;
    }
}
