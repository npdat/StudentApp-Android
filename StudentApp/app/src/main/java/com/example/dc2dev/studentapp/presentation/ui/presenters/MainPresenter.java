package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.domain.entities.service.StudentService;
import com.example.dc2dev.studentapp.presentation.ui.views.MainView;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/15/17.
 */

public class MainPresenter {
    private MainView view;
    private StudentService service;

    public MainPresenter(MainView view, StudentService service) {
        this.view = view;
        this.service = service;
    }
    public void onCreateClicked(){
        view.intenttocreatest();
    }
    public void oUpdateClicked(int pos){
        view.intenttoupdatest(pos);
    }
    public void onDeleteClicked(String id){
        service.delete(id);
        view.deleteclicked(id);
    }
    public ArrayList<Student> onGetList(){
        return service.getlistst();
    }
}
