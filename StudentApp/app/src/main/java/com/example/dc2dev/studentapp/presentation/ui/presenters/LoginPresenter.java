package com.example.dc2dev.studentapp.presentation.ui.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.presentation.ui.views.LoginView;

import static com.example.dc2dev.studentapp.domain.entities.Member.isEmailValid;

/**
 * Created by npdat on 6/14/2017.
 */

public class LoginPresenter {

    private LoginView view;
    private MemberDataService service;

    public LoginPresenter(LoginView view, MemberDataService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoginClicked() {
        String email = view.getEmail();
        String password = view.getPassword();


        if (email.isEmpty()) {
            view.showEmailError(R.string.email_empty);
            return;
        } else if (!isEmailValid(email.toString())) {
            view.showEmailError(R.string.email_invalid);
            return;
        }
        else if (password.isEmpty()){
            view.showEmailError(R.string.password_empty);
            return;
        }

        boolean isLogin = service.login(email, password);

        if (isLogin) {
            view.navigationToHome();
        } else  {
            view.loginFail();
        }
    }
    public void onSignUpClicked(){
        view.intenttosignup();
    }
}
