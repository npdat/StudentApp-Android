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
import com.example.dc2dev.studentapp.data.clients.database.TableMember;
import com.example.dc2dev.studentapp.domain.entities.Member;

import java.util.List;

import static com.example.dc2dev.studentapp.domain.entities.Member.isEmailValid;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivityLogin extends AppCompatActivity {
    EditText editemail,editpassword;
    Button btnlogin,btnsu;
    TableMember tableMember;
    List<Member> members;
    ProgressDialog progressDialog;
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
        tableMember=new TableMember(ActivityLogin.this);
       //tableMember.create(new Member("minh","minh","123",0));

        progressDialog=new ProgressDialog(ActivityLogin.this);
        progressDialog.setMessage("Xin Vui Long Cho...");
    }
    public void Listener(){
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Login();

            }
        });
        btnsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityLogin.this,ActivitySignUp.class);
                startActivity(intent);
            }
        });
    }
    public void Login(){
        members=tableMember.getListMember();
        if(editemail.getText().toString().equals("")||editpassword.getText().toString().equals("")) {
            progressDialog.dismiss();
            Toast.makeText(ActivityLogin.this,"Khong duoc bo trong",Toast.LENGTH_SHORT).show();
        }
        if(!isEmailValid(editemail.getText().toString())) {
            progressDialog.dismiss();
            Toast.makeText(ActivityLogin.this,"Email khong dung dinh dang",Toast.LENGTH_SHORT).show();
        }
        for (int i=members.size()-1;i>=0;i--){
            if(editemail.getText().toString().equals(members.get(i).getEmail().toString())
                    &&editpassword.getText().toString().equals(members.get(i).getPassword().toString())){
                Toast.makeText(ActivityLogin.this,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent intent = new Intent(ActivityLogin.this,Activitymain.class);
                startActivity(intent);
                break;
            }
            if(i==0){
                if(!editemail.getText().toString().equals(members.get(i).getEmail().toString())
                        ||!editpassword.getText().toString().equals(members.get(i).getPassword().toString())){
                    Toast.makeText(ActivityLogin.this,"Email hoac mat khau sai",Toast.LENGTH_SHORT).show();
                    editemail.setFocusable(true);
                    progressDialog.dismiss();
                }

            }
        }
    }
}