package com.example.dc2dev.studentapp.presentation.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.dc2dev.studentapp.data.clients.database.TableClass;
import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class ActivityUpdateStudent extends AppCompatActivity {
    EditText txtnameu;
    Spinner spinclassu;
    Button btnupdate;
    Intent intent;
    ImageView imgchooseu;
    String classchoose,picturePath,classit,imgit;
    int id,posclass;
    Uri uriu=null;
    List<String> classadap;
    ArrayList<Class> classs;
    TableClass tableClass;
    TableStudent tableStudent;
    private final int SELECT_PHOTO=1;
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
        imgchooseu= (ImageView) findViewById(R.id.imgchooseu);
        btnupdate= (Button) findViewById(R.id.btnupdate);
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
//load img
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(Uri.parse(imgit),filePathColumn, null, null, null);
        cursor.moveToFirst();
        cursor.close();
        Bitmap bmp= BitmapByte.uritoBM(Uri.parse(imgit),ActivityUpdateStudent.this);
        imgchooseu.setImageBitmap(bmp);


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
       imgchooseu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
       });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtnameu.getText().toString().equals("")){
                    if(uriu==null){
                        Student student=new Student(id,txtnameu.getText().toString(),classchoose,imgit);
                        tableStudent.update(student);
                        Toast.makeText(ActivityUpdateStudent.this,"Cap nhat thanh cong ",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Student student=new Student(id,txtnameu.getText().toString(),classchoose,uriu.toString());
                        tableStudent.update(student);
                        Toast.makeText(ActivityUpdateStudent.this,"Cap nhat thanh cong ",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else {
                    Toast.makeText(ActivityUpdateStudent.this,"Ban cua nhap ten",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PHOTO&&data!=null){
            uriu=data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uriu,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bmp= BitmapByte.uritoBM(uriu,ActivityUpdateStudent.this);
            imgchooseu.setImageBitmap(bmp);
        }
    }
}
