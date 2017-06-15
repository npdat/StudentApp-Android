package com.example.dc2dev.studentapp.data.clients.service;

import android.content.Context;

import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.domain.entities.service.StudentService;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/15/17.
 */

public class StudentDataService implements StudentService {
    TableStudent tableStudent;

    public StudentDataService(Context context) {
        tableStudent = new TableStudent(context);
    }

    @Override
    public boolean delete(String id) {
        boolean isdel = tableStudent.delete(id);
        return isdel;
    }

    @Override
    public ArrayList<Student> getlistst() {
        return tableStudent.getListStudent();
    }

    @Override
    public boolean createst(Student student) {
        return tableStudent.create(student);
    }
    public boolean updatest(Student student) {
        return tableStudent.update(student);
    }


}
