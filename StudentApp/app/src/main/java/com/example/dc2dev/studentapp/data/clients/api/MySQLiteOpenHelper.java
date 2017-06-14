package com.example.dc2dev.studentapp.data.clients.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dc2dev.studentapp.data.clients.database.TableClass;
import com.example.dc2dev.studentapp.data.clients.database.TableMember;
import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Class;
import com.example.dc2dev.studentapp.domain.entities.Student;

/**
 * Created by dc2dev on 6/9/17.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context) {
        super(context, "database", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableStudent.TAO_BANG);
        db.execSQL(TableMember.TAO_BANG);
        db.execSQL(TableClass.TAO_BANG);
    }
    public void insertdata(Context context){
        TableStudent tableStudent=new TableStudent(context);
        TableClass tableClass=new TableClass(context);
        tableStudent.create(new Student("test1","Lop 1","http://i.imgur.com/DvpvklR.png"));
        tableStudent.create(new Student("test2","Lop 2","http://i.imgur.com/DvpvklR.png"));
        tableStudent.create(new Student("test3","Lop 2","http://i.imgur.com/DvpvklR.png"));
        tableStudent.create(new Student("test4","Lop 3","http://i.imgur.com/DvpvklR.png"));
        tableClass.create(new Class("Lop 1"));
        tableClass.create(new Class("Lop 2"));
        tableClass.create(new Class("Lop 3"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableStudent.TEN_BANG);
        db.execSQL("DROP TABLE IF EXISTS " + TableMember.TEN_BANG);
        db.execSQL("DROP TABLE IF EXISTS " + TableClass.TEN_BANG);
        onCreate(db);
    }
}
