package com.example.dc2dev.studentapp.data.service;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by dc2dev on 6/16/17.
 */
@RunWith(AndroidJUnit4.class)
public class TableClassTest {
    private Context context;
    private ClassDataService service;
    @Before
    public void SetUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        service=new ClassDataService(context);
    }
    @Test
    public void CheckGetListCl() throws Exception{
        Assert.assertNotNull(service.getlistclas());
    }

}
