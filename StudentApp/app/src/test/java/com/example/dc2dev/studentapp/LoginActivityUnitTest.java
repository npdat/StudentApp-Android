package com.example.dc2dev.studentapp;

import com.example.dc2dev.studentapp.presentation.ui.activities.ActivityLogin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by dc2dev on 6/14/17.
 */
@RunWith(JUnit4.class)
public class LoginActivityUnitTest {
    private ActivityLogin activityLogin;

    @Before
    public void Setup (){
        activityLogin=new ActivityLogin();

    }
    @Test
    public void LoginTest(){
        Assert.assertEquals(1,activityLogin.Login("",""));//fail khi bo trong
        Assert.assertEquals(2,activityLogin.Login("m@","123"));//fail khi khong dung dinh dang
        Assert.assertEquals(3,activityLogin.Login("m@gmail.com","123"));//dung mat khau
        Assert.assertEquals(4,activityLogin.Login("m@gmail.com","1234"));//sai mat khau
    }
}
