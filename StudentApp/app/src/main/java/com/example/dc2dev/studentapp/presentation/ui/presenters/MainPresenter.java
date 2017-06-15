package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.domain.entities.service.StudentService;
import com.example.dc2dev.studentapp.presentation.ui.views.MainView;

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
//    public void onLoginClicked() {
//        String email = view.getEmail();
//        String password = view.getPassword();
//
//
//        if (email.isEmpty()) {
//            view.showEmailError(R.string.email_empty);
//            return;
//        } else if (!isEmailValid(email.toString())) {
//            view.showEmailError(R.string.email_invalid);
//            return;
//        }
//        else if (password.isEmpty()){
//            view.showEmailError(R.string.password_empty);
//            return;
//        }
//
//        boolean isLogin = service.login(email, password);
//
//        if (isLogin) {
//            view.navigationToHome();
//        } else  {
//            view.loginFail();
//        }
//    }
    public void onCreateClicked(){
        view.intenttocreatest();
    }
    //toi day
    public void onDeleteClicked(String id){
        boolean isdelete = service.delete(id);
        view.deleteclicked(id);
    }
}
