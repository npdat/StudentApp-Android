package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.presentation.ui.adapters.StudentAdapter;
import com.example.dc2dev.studentapp.presentation.ui.presenters.MainPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.MainView;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class Activitymain extends AppCompatActivity implements MainView{
    public final int KEYUPDATE=123;
    ListView listView;
    FloatingActionButton floatingActionButton;
    StudentAdapter studentAdapter;
    ArrayList<Student> students,studentssearch;
    MainPresenter presenter;
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
        students=new ArrayList<>();
        studentssearch=new ArrayList<>();
        presenter=new MainPresenter(Activitymain.this,new StudentDataService(Activitymain.this));
        students=presenter.onGetList();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
    }
    public void Listener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateClicked();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.oUpdateClicked(position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String idst=students.get(position).getId()+"";
            presenter.onDeleteClicked(idst);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        students=new ArrayList<>();
        students=presenter.onGetList();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
        super.onResume();
    }

    @Override
    protected void onRestart() {
        students=new ArrayList<>();
        students=presenter.onGetList();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_liststudnet, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("m", newText);
                studentssearch=new ArrayList<Student>();
                for(Student st :students){
                    if(st.getFullname().contains(newText)||st.getClassname().contains(newText)){
                        studentssearch.add(st);
                        studentAdapter=new StudentAdapter(Activitymain.this,studentssearch);
                        listView.setAdapter(studentAdapter);
                    }
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void intenttocreatest() {
        Intent intent=new Intent(Activitymain.this,ActivityCreateStudent.class);
        startActivity(intent);
    }

    @Override
    public void intenttoupdatest(int pos) {
        Intent intent=new Intent(Activitymain.this,ActivityUpdateStudent.class);
        intent.putExtra("name",students.get(pos).getFullname());
        intent.putExtra("class",students.get(pos).getClassname());
        intent.putExtra("img",students.get(pos).getImage());
        intent.putExtra("id",students.get(pos).getId());
        startActivityForResult(intent,KEYUPDATE);
    }

    @Override
    public void deleteclicked(String id) {
        students=new ArrayList<>();
        students=presenter.onGetList();
        studentAdapter=new StudentAdapter(Activitymain.this,students);
        listView.setAdapter(studentAdapter);
        Toast.makeText(Activitymain.this,"Xoa thanh cong",Toast.LENGTH_SHORT).show();
    }


}
