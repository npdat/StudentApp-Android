package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
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

public class ActivityUpdateStudent extends AppCompatActivity {
    EditText txtnameu;
    Spinner spinclassu,spinimgu;
    Button btnupdate;
    Intent intent;
    String classchoose,imgchoose,classit,imgit;
    int id,posclass,posimg;
    List<String> classadap;
    List<String> listimg;
    ArrayList<Class> classs;
    TableClass tableClass;
    TableStudent tableStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatestudent_activity);
        Init();
        Listener();
    }
    public void Init(){
        txtnameu= (EditText) findViewById(R.id.txtnameu);
        spinclassu= (Spinner) findViewById(R.id.spinclassu);
        spinimgu= (Spinner) findViewById(R.id.spinimgu);
        btnupdate= (Button) findViewById(R.id.btnupdate);
        listimg=new ArrayList<>();
        classadap=new ArrayList<>();
        intent=getIntent();
        id=intent.getIntExtra("id",0);
        classit=intent.getStringExtra("class");
        imgit=intent.getStringExtra("img");
        txtnameu.setText(intent.getStringExtra("name"));
        tableStudent=new TableStudent(ActivityUpdateStudent.this);
        tableClass=new TableClass(ActivityUpdateStudent.this);
        classs=tableClass.getListClass();
        classadap=new ArrayList<>();
        for(Class c :classs){
            classadap.add(c.getName());
        }
        spinclassu.setAdapter(new ArrayAdapter<String>(ActivityUpdateStudent.this,R.layout.support_simple_spinner_dropdown_item,classadap));
        for (int i=0;i<classadap.size();i++){
            if (classadap.get(i).toString().equals(classit.toString())){
                posclass=i;
                spinclassu.setSelection(i);
            }
        }
        listimg.add("Hinh 1");//http://i.imgur.com/DvpvklR.png
        listimg.add("Hinh 2");
        spinimgu.setAdapter(new ArrayAdapter<String>(ActivityUpdateStudent.this,R.layout.support_simple_spinner_dropdown_item,listimg));
        for (int i=0;i<listimg.size();i++){
            if (listimg.get(i).toString().equals(imgit.toString())){
                posimg=i;
                spinimgu.setSelection(i);
            }
        }

    }
    public void Listener(){
        spinclassu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinimgu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtnameu.getText().toString().equals("")){
                    Student student=new Student(id,txtnameu.getText().toString(),classchoose,imgchoose);
                    tableStudent.update(student);
                    Toast.makeText(ActivityUpdateStudent.this,"Cap nhat thanh cong ",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(ActivityUpdateStudent.this,"Ban cua nhap ten",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
