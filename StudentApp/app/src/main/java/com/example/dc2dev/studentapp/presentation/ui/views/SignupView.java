package com.example.dc2dev.studentapp.presentation.ui.views;

/**
 * Created by dc2dev on 6/15/17.
 */

public interface SignupView {
    String getFullName();
    String getEmail();
    String getPassword();
    String getCfPassword();
    int getCbgender();
    void navigationTolistst();
    void showEmailError(int resId);
}
