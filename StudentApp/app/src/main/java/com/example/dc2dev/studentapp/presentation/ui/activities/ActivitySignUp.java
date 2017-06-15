package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.presentation.ui.presenters.SignupPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.SignupView;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivitySignUp extends AppCompatActivity implements SignupView{
    EditText editname,editemail,editpass,editcfpass;
    Button btnsignup;
    RadioButton cbmale,cbfemale;
    SignupPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        Init();
        Listener();
    }
    public void Init(){
        editname= (EditText) findViewById(R.id.txtfullnamer);
        editemail= (EditText) findViewById(R.id.txtemailr);
        editpass= (EditText) findViewById(R.id.txtpasswordr);
        editcfpass= (EditText) findViewById(R.id.txtcfpass);
        btnsignup= (Button) findViewById(R.id.btnsignup);
        cbmale= (RadioButton) findViewById(R.id.cbmale);
        cbfemale= (RadioButton) findViewById(R.id.cbfemale);
        presenter=new SignupPresenter(ActivitySignUp.this,new MemberDataService(ActivitySignUp.this));
    }
    public void Listener(){
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpClicked();
            }
        });
    }


    @Override
    public String getFullName() {
        return editname.getText().toString();
    }

    @Override
    public String getEmail() {
        return editemail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editpass.getText().toString();
    }

    @Override
    public String getCfPassword() {
        return editcfpass.getText().toString();
    }

    @Override
    public int getCbgender() {
        if(cbmale.isChecked()) {
            return 0;
        }
        return 1;

    }
  @Override
    public void navigationTolistst() {
        Toast.makeText(ActivitySignUp.this,"Dang ki thanh cong",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ActivitySignUp.this,ActivityLogin.class);
        startActivity(intent);
    }

    @Override
    public void showError(int resId) {
        Toast.makeText(ActivitySignUp.this,getString(resId),Toast.LENGTH_SHORT).show();
    }
}
