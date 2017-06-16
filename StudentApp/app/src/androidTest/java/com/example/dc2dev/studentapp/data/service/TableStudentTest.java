package com.example.dc2dev.studentapp.data.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.database.TableStudent;
import com.example.dc2dev.studentapp.domain.entities.Student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by dc2dev on 6/16/17.
 */
@RunWith(AndroidJUnit4.class)
public class TableStudentTest {
    private Context context;
    private SQLiteDatabase database;
    private TableStudent tableStudent;
    private Student studentc,studentu;
    private int idst;
    @Before
    public void SetUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        database = new MySQLiteOpenHelper(context).getWritableDatabase();
        tableStudent=new TableStudent(context);
        studentc=new Student(10,"minh","Lop 1","content://com.android.providers.media.documents/document/image%3A81");
        studentu=new Student(12,"minh","Lop 1","content://com.android.providers.media.documents/document/image%3A81");
        idst=1;
    }
    @Test
    public void CheckCreateSt() throws Exception{
        Assert.assertEquals(true,tableStudent.create(studentc));
        Assert.assertEquals(10,tableStudent.creatertid(studentc));
    }
    @Test
    public void CheckUpdateSt() throws Exception{
        Assert.assertEquals(true,tableStudent.update(studentu));
        Assert.assertEquals(12,tableStudent.updatertid(studentc));
    }
    @Test
    public void CheckDeleteSt() throws Exception{
        Assert.assertEquals(true,tableStudent.delete(String.valueOf(idst)));
    }
    @Test
    public void CheckGetListSt() throws Exception{
        Assert.assertNotNull(tableStudent.getListStudent());
    }
}
