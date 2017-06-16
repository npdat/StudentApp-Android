package com.example.dc2dev.studentapp.data.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.database.TableMember;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.domain.entities.Member;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by dc2dev on 6/16/17.
 */
@RunWith(AndroidJUnit4.class)
public class TableMemberTest {
    private Context context;
    private SQLiteDatabase database;
    private MemberDataService service;
    private TableMember tableMember;
    private Member memberc;
    private String email,password;
    @Before
    public void SetUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        database = new MySQLiteOpenHelper(context).getWritableDatabase();
        tableMember=new TableMember(context);
        service=new MemberDataService(context);
        memberc=new Member(10,"minhhhh","test@gmail.com","123",0);
        email="m@gmail.com";
        password="123";
    }
    @Test
    public void CheckSignUp() throws Exception{
        Assert.assertEquals(true,service.signup(memberc));
        Assert.assertEquals(10,tableMember.creatertid(memberc));
    }

    @Test
    public void CheckLogin() throws Exception{
        Assert.assertEquals(true,service.login(email,password));
    }
}
