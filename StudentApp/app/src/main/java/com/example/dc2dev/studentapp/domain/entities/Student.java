package com.example.dc2dev.studentapp.domain.entities;

/**
 * Created by dc2dev on 6/9/17.
 */

public class Student {
    private int id;
    private String fullname;
    private String classname;
    private String image;
    public Student(int id,String fullname,String classname,String image){
        this.fullname=fullname;
        this.classname=classname;
        this.image=image;
        this.id=id;
    }
    public Student(String fullname,String classname,String image){
        this.fullname=fullname;
        this.classname=classname;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getClassname() {
        return classname;
    }

    public String getFullname() {
        return fullname;
    }
}
