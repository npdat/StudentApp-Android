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
import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.database.TableMember;
import com.example.dc2dev.studentapp.domain.entities.Member;

import static com.example.dc2dev.studentapp.domain.entities.Member.isEmailValid;
import static com.example.dc2dev.studentapp.domain.entities.Member.isFullNameValid;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivitySignUp extends AppCompatActivity {
    EditText editname,editemail,editpass,editcfpass;
    Button btnsignup;
    RadioButton cbmale,cbfemale;
    TableMember tableMember;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Init();
        Listener();
    }
    public void Init(){
        mySQLiteOpenHelper=new MySQLiteOpenHelper(ActivitySignUp.this);
        mySQLiteOpenHelper.insertdata(ActivitySignUp.this);
        editname= (EditText) findViewById(R.id.txtfullnamer);
        editemail= (EditText) findViewById(R.id.txtemailr);
        editpass= (EditText) findViewById(R.id.txtpasswordr);
        editcfpass= (EditText) findViewById(R.id.txtcfpass);
        btnsignup= (Button) findViewById(R.id.btnsignup);
        cbmale= (RadioButton) findViewById(R.id.cbmale);
        cbfemale= (RadioButton) findViewById(R.id.cbfemale);
        tableMember=new TableMember(ActivitySignUp.this);
    }
    public void Listener(){
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editname.getText().toString().equals("")
                        ||editemail.getText().toString().equals("")
                        ||editpass.getText().toString().equals("")
                        ||editcfpass.getText().toString().equals("")){
                    Toast.makeText(ActivitySignUp.this,"Khong duoc bo trong",Toast.LENGTH_SHORT).show();
                }
                else{
                        if(isFullNameValid(editname.getText().toString())){
                            Toast.makeText(ActivitySignUp.this,"Ten phai nhieu hon 6 ki tu",Toast.LENGTH_SHORT).show();
                        }
                        else {
                        if(!isEmailValid(editemail.getText().toString())) {
                            Toast.makeText(ActivitySignUp.this,"Email khong dung dinh dang",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(editpass.getText().toString().equals(editcfpass.getText().toString())){
                                if(cbmale.isChecked()){
                                    tableMember.create(new Member(editname.getText().toString(),editemail.getText().toString(),editpass.getText().toString(),1));
                                    Toast.makeText(ActivitySignUp.this,"Dang ki thanh cong",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(ActivitySignUp.this,ActivityLogin.class);
                                    startActivity(intent);
                                }
                                else if(cbfemale.isChecked()){
                                    tableMember.create(new Member(editname.getText().toString(),editemail.getText().toString(),editpass.getText().toString(),0));
                                    Toast.makeText(ActivitySignUp.this,"Dang ki thanh cong",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(ActivitySignUp.this,ActivityLogin.class);
                                    startActivity(intent);
                                }
                            }
                            else {
                                Toast.makeText(ActivitySignUp.this,"Mat khau xac nhan khong dung",Toast.LENGTH_SHORT).show();
                            }
                        }

                        }
                    }
            }
        });
    }


}
