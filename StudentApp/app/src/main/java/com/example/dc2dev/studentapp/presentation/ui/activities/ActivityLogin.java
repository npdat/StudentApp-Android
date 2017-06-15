package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.presentation.ui.presenters.LoginPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.LoginView;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivityLogin extends AppCompatActivity implements LoginView {
    EditText editemail,editpassword;
    Button btnlogin,btnsu;
    ProgressDialog progressDialog;
    LoginPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Init();
        Listener();
    }
    public void Init(){

        btnsu= (Button) findViewById(R.id.btnsu);
        editemail= (EditText) findViewById(R.id.txtemail);
        editpassword= (EditText) findViewById(R.id.txtpassword);
        btnlogin= (Button) findViewById(R.id.btnlogin);
        presenter = new LoginPresenter(this, new MemberDataService(ActivityLogin.this));
        progressDialog=new ProgressDialog(ActivityLogin.this);
        progressDialog.setMessage("Xin Vui Long Cho...");
    }
    public void Listener(){
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                presenter.onLoginClicked();

            }
        });
        btnsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpClicked();
            }
        });
    }
    @Override
    public String getEmail() {
        return editemail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editpassword.getText().toString();
    }

    @Override
    public void showEmailError(int resId) {
        progressDialog.dismiss();
        Toast.makeText(ActivityLogin.this,getString(resId),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigationToHome() {
        progressDialog.dismiss();
        Toast.makeText(ActivityLogin.this, getString(R.string.login_sucess),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActivityLogin.this, Activitymain.class);
        startActivity(intent);
    }

    @Override
    public void intenttosignup() {
        Intent intent=new Intent(ActivityLogin.this,ActivitySignUp.class);
        startActivity(intent);
    }

    @Override
    public void loginFail() {
        progressDialog.dismiss();
        Toast.makeText(ActivityLogin.this,getString(R.string.login_fail),Toast.LENGTH_SHORT).show();
    }
}
