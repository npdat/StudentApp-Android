package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.api.BitmapByte;
import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.presentation.ui.presenters.CreateStPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.CreateStView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc2dev on 6/9/17.
 */

public class ActivityCreateStudent extends AppCompatActivity implements CreateStView{

    ImageView img;
    EditText txtnamec;
    Spinner spinnerc;
    Button btnc;
    ArrayList<Class> classs;
    String classchoose;
    List<String> classadap;
    Uri uric;
    String picturePath;
    CreateStPresenter presenter;
    private final int SELECT_PHOTO=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createstudent_activity);
        Init();
        Listener();
    }
    public void Init(){
        img= (ImageView) findViewById(R.id.imgchoose);
        txtnamec= (EditText) findViewById(R.id.txtnamec);
        spinnerc= (Spinner) findViewById(R.id.spinclassc);
        btnc= (Button) findViewById(R.id.btncreate);
        presenter=new CreateStPresenter(ActivityCreateStudent.this,new StudentDataService(ActivityCreateStudent.this),
                new ClassDataService(ActivityCreateStudent.this));
        classs=presenter.onGetList();
        classadap=new ArrayList<>();
        for(Class c :classs){
            classadap.add(c.getName());
        }
        spinnerc.setAdapter(new ArrayAdapter<String>(ActivityCreateStudent.this,R.layout.support_simple_spinner_dropdown_item,classadap));

    }
    public void Listener(){
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            presenter.isCreateClicked();
            }
        });
        spinnerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               presenter.getClasschoose(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classchoose=classs.get(0).getName();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              presenter.GetImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PHOTO&&data!=null){
            uric=data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uric,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bmp= BitmapByte.uritoBM(uric,ActivityCreateStudent.this);
            img.setImageBitmap(bmp);
        }
    }


    @Override
    public String getFullName() {
        return txtnamec.getText().toString();
    }

    @Override
    public String getClas() {
        return classchoose ;
    }

    @Override
    public String getImg() {
        return uric.toString();
    }

    @Override
    public void getImage() {
        Intent intent;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else{
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    public void getchooseclass(int pos) {
        switch (pos){
            case 0:
                classchoose=classs.get(pos).getName();
                break;
            case 1:
                classchoose=classs.get(pos).getName();
                break;
            case 2:
                classchoose=classs.get(pos).getName();
                break;
        }
    }
    @Override
    public void showError(int resId) {
        Toast.makeText(ActivityCreateStudent.this,getString(resId),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backhome() {
        Toast.makeText(ActivityCreateStudent.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
        finish();
    }

}
