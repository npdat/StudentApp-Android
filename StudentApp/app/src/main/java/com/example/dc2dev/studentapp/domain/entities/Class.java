package com.example.dc2dev.studentapp.domain.entities;

/**
 * Created by dc2dev on 6/9/17.
 */

public class Class {
    private int id;
    private String name;
    public Class(int id,String name){
        this.name=name;
        this.id=id;
    }
    public Class(String name){
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
