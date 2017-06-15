package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.domain.entities.service.ClassService;
import com.example.dc2dev.studentapp.domain.entities.service.StudentService;
import com.example.dc2dev.studentapp.presentation.ui.views.CreateStView;

import java.util.ArrayList;

/**
 * Created by Minhhoang on 6/15/2017.
 */

public class CreateStPresenter {
    private CreateStView view;
    private StudentService service;
    private ClassService serviceclass;
    public CreateStPresenter(CreateStView view, StudentService service,ClassService serviceclass) {
        this.view = view;
        this.service = service;
        this.serviceclass=serviceclass;
    }
    public void isCreateClicked() {
        String name = view.getFullName();
        String cl = view.getClas();
        String img=view.getImg();

        if (name.isEmpty()) {
            view.showError(R.string.fullname_empty);
            return;
        }
        service.createst(new Student(name,cl,img));
        view.backhome();

    }
    public ArrayList<Class> onGetList(){
        return serviceclass.getlistclas();
    }
    public void GetImage(){
        view.getImage();

    }
    public void getClasschoose(int pos){
        view.getchooseclass(pos);
    }
}
