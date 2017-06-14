package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.presentation.ui.adapters.StudentAdapter;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class Activitymain extends AppCompatActivity {
    public final int KEYUPDATE=123;
    ListView listView;
    FloatingActionButton floatingActionButton;
    StudentAdapter studentAdapter;
    ArrayList<Student> students;
    TableStudent tableStudent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Init();
        Listener();
    }
    public void Init(){
        listView= (ListView) findViewById(R.id.listsd);
        floatingActionButton= (FloatingActionButton) findViewById(R.id.btnfloat);
        tableStudent=new TableStudent(Activitymain.this);
        students=new ArrayList<>();
        students=tableStudent.getListStudent();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
    }
    public void Listener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activitymain.this,ActivityCreateStudent.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activitymain.this,ActivityUpdateStudent.class);
                intent.putExtra("name",students.get(position).getFullname());
                intent.putExtra("class",students.get(position).getClassname());
                intent.putExtra("img",students.get(position).getImage());
                intent.putExtra("id",students.get(position).getId());
                startActivityForResult(intent,KEYUPDATE);
            }
        });
    }

    @Override
    protected void onResume() {
        students=new ArrayList<>();
        students=tableStudent.getListStudent();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
        super.onResume();
    }

    @Override
    protected void onRestart() {
        students=new ArrayList<>();
        students=tableStudent.getListStudent();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
        super.onRestart();
    }
}
