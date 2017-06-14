package com.example.dc2dev.studentapp.domain.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dc2dev on 6/9/17.
 */

public class Member {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private int gender;
    public Member(int id,String fullname,String email,String password,int gender){
        this.fullname=fullname;
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.id=id;
    }
    public Member(String fullname,String email,String password,int gender){
        this.fullname=fullname;
        this.email=email;
        this.password=password;
        this.gender=gender;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isFullNameValid(String name) {
        if(name.toString().length()<6){
            return true;
        }
        else
            return false;
    }
}
