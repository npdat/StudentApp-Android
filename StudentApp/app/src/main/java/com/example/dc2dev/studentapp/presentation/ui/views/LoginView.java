package com.example.dc2dev.studentapp.presentation.ui.views;

/**
 * Created by npdat on 6/14/2017.
 */

public interface LoginView {
    String getEmail();
    String getPassword();
    void showEmailError(int resId);
    void navigationToHome();
    void loginFail();
}
