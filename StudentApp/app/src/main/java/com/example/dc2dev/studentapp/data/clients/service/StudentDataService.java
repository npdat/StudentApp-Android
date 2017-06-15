package com.example.dc2dev.studentapp.data.clients.service;

import android.content.Context;

import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.service.StudentService;

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
        boolean isLogin = tableStudent.delete(id);
        return isLogin;
    }


}
