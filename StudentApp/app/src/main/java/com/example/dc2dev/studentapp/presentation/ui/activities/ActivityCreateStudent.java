package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.database.TableClass;
import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivityCreateStudent extends AppCompatActivity{

    Spinner spinimg;
    EditText txtnamec;
    Spinner spinnerc;
    Button btnc;
    TableClass tableClass;
    TableStudent tableStudent;
    ArrayList<Class> classs;
    String classchoose,imgchoose;
    List<String> classadap;
    List<String> listimg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createstudent_activity);
        Init();
        Listener();
    }
    public void Init(){
        listimg=new ArrayList<>();
        spinimg= (Spinner) findViewById(R.id.spinimg);
        txtnamec= (EditText) findViewById(R.id.txtnamec);
        spinnerc= (Spinner) findViewById(R.id.spinclassc);
        btnc= (Button) findViewById(R.id.btncreate);
        tableStudent=new TableStudent(ActivityCreateStudent.this);
        tableClass=new TableClass(ActivityCreateStudent.this);
        classs=tableClass.getListClass();
        classadap=new ArrayList<>();
        for(Class c :classs){
            classadap.add(c.getName());
        }
        spinnerc.setAdapter(new ArrayAdapter<String>(ActivityCreateStudent.this,R.layout.support_simple_spinner_dropdown_item,classadap));
        listimg.add("Hinh 1");//http://i.imgur.com/DvpvklR.png
        listimg.add("Hinh 2");
        spinimg.setAdapter(new ArrayAdapter<String>(ActivityCreateStudent.this,R.layout.support_simple_spinner_dropdown_item,listimg));
    }
    public void Listener(){
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtnamec.getText().toString().equals("")){
                    tableStudent.create(new Student(txtnamec.getText().toString(),classchoose,imgchoose));
                    Toast.makeText(ActivityCreateStudent.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ActivityCreateStudent.this,Activitymain.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ActivityCreateStudent.this,"Ban chua nhap ten",Toast.LENGTH_SHORT).show();
                }

            }
        });
        spinnerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        classchoose=classs.get(position).getName();
                        break;
                    case 1:
                        classchoose=classs.get(position).getName();
                        break;
                    case 2:
                        classchoose=classs.get(position).getName();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classchoose=classs.get(0).getName();
            }
        });
        spinimg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        imgchoose="http://i.imgur.com/DvpvklR.png";
                        break;
                    case 1:
                        imgchoose="http://i157.photobucket.com/albums/t61/goodcow/avata.jpg";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
