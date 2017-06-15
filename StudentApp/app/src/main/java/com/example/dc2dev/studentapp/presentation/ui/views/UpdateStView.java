package com.example.dc2dev.studentapp.presentation.ui.views;

/**
 * Created by Minhhoang on 6/16/2017.
 */

public interface UpdateStView {
    int getid();
    String getFullName();
    String getClas();
    String getImg();
    String getImgNull();
    void getImage();
    void getchooseclass(int pos);
    void getData();
    void showError(int resId);
    void updatesusscess();
}
