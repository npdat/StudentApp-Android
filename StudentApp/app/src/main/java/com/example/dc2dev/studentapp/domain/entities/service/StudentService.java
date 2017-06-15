package com.example.dc2dev.studentapp.domain.entities.service;

import com.example.dc2dev.studentapp.domain.entities.Student;

import java.util.ArrayList;

/**
 * Created by npdat on 6/14/2017.
 */

public interface StudentService {
    boolean delete(String id);
    ArrayList<Student> getlistst();
    boolean createst(Student student);
    boolean updatest(Student student);
}
