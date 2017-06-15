package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.domain.entities.Member;
import com.example.dc2dev.studentapp.presentation.ui.views.SignupView;

import static com.example.dc2dev.studentapp.domain.entities.Member.isConfirmPassword;
import static com.example.dc2dev.studentapp.domain.entities.Member.isEmailValid;
import static com.example.dc2dev.studentapp.domain.entities.Member.isFullNameValid;

/**
 * Created by dc2dev on 6/15/17.
 */

public class SignupPresenter {
    private SignupView view;
    private MemberDataService service;

    public SignupPresenter(SignupView view, MemberDataService service) {
        this.view = view;
        this.service = service;
    }
    public void onSignUpClicked() {
        String fullname=view.getFullName();
        String email = view.getEmail();
        String password = view.getPassword();
        String cfpassword=view.getCfPassword();
        int gender=view.getCbgender();

        if (!isFullNameValid(fullname)) {
            view.showError(R.string.fullname_invalid);
            return;
        }
        else if (!isEmailValid(email.toString())) {
            view.showError(R.string.email_invalid);
            return;
        }
        else if (password.isEmpty()){
            view.showError(R.string.password_empty);
            return;
        }
        else if (cfpassword.isEmpty()){
            view.showError(R.string.cfpassword_empty);
            return;
        }
        else if (isConfirmPassword(password,cfpassword)){
            view.showError(R.string.cfpassword_invalid);
            return;
        }

        boolean signupsuccess = service.signup(new Member(fullname,email,password,gender));

        if (signupsuccess) {
            view.navigationTolistst();
        }
    }
}
