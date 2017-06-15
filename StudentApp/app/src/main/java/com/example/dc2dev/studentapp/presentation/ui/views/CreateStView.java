package com.example.dc2dev.studentapp.presentation.ui.views;

/**
 * Created by Minhhoang on 6/15/2017.
 */

public interface CreateStView {
    String getFullName();
    String getClas();
    String getImg();
    void getImage();
    void getchooseclass(int pos);
    void showError(int resId);
    void backhome();
}
