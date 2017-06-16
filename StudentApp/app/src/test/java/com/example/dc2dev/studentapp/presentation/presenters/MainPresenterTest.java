package com.example.dc2dev.studentapp.presentation.presenters;

import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.presentation.ui.activities.Activitymain;
import com.example.dc2dev.studentapp.presentation.ui.presenters.MainPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by dc2dev on 6/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainView view;
    @Mock
    private MainPresenter presenter;
    private Activitymain activitymain;
    private SQLiteDatabase database;

    @Before
    public void setUp() throws Exception {
        activitymain = new Activitymain();
        presenter = new MainPresenter(view, new StudentDataService(activitymain));
        database = new MySQLiteOpenHelper(activitymain).getWritableDatabase();
    }
    @Test
    public void CheckIntenttoCreate(){
        presenter.onCreateClicked();
        verify(view).intenttocreatest();
    }
    @Test
    public void CheckIntenttoUpdate(){
        int pos=0;
        presenter.oUpdateClicked(pos);
        verify(view).intenttoupdatest(pos);
    }
//    @Test
//    public void CheckGetList(){
//        boolean check=false;
//        ArrayList<Student> students=presenter.onGetList();
//            if(students.size()>0){
//                check=false;
//            }
//            else
//                check=true;
//        Assert.assertEquals(check,false);
//    }


    //
}
