package com.example.dc2dev.studentapp.data.clients.service;

import android.content.Context;

import com.example.dc2dev.studentapp.data.clients.database.TableClass;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.service.ClassService;

import java.util.ArrayList;

/**
 * Created by Minhhoang on 6/15/2017.
 */

public class ClassDataService implements ClassService {
    TableClass tableClass;

    public ClassDataService(Context context) {
        tableClass = new TableClass(context);
    }
    @Override
    public ArrayList<Class> getlistclas() {
        return tableClass.getListClass();
    }
}
