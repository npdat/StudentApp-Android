package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.domain.entities.service.ClassService;
import com.example.dc2dev.studentapp.domain.entities.service.StudentService;
import com.example.dc2dev.studentapp.presentation.ui.views.UpdateStView;

import java.util.ArrayList;

/**
 * Created by Minhhoang on 6/16/2017.
 */

public class UpdateStPresenter {
    private UpdateStView view;
    private StudentService service;
    private ClassService serviceclass;
    public UpdateStPresenter(UpdateStView view, StudentService service, ClassService serviceclass) {
        this.view = view;
        this.service = service;
        this.serviceclass=serviceclass;
    }
    public void isUpdateClicked() {
        int id=view.getid();
        String name = view.getFullName();
        String cl = view.getClas();
        String img=view.getImg();
        String imgnull=view.getImgNull();

        if (name.isEmpty()) {
            view.showError(R.string.fullname_empty);
            return;
        }
        else if(img==null){
            service.updatest(new Student(id,name,cl,imgnull));

        }
        else if(img!=null){
            service.updatest(new Student(id,name,cl,img));

        }
        view.updatesusscess();


    }
    public void getDataIntent() {
        view.getData();
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
