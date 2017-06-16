package com.example.dc2dev.studentapp.presentation.presenters;

import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.presentation.ui.activities.ActivityCreateStudent;
import com.example.dc2dev.studentapp.presentation.ui.presenters.CreateStPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.CreateStView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dc2dev on 6/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateStTest {

    @Mock
    private CreateStView view;
    @Mock
    private CreateStPresenter presenter;
    private ActivityCreateStudent activityCreateStudent;
    private SQLiteDatabase database;



    @Before
    public void setUp() throws Exception {
        activityCreateStudent = new ActivityCreateStudent();
        presenter = new CreateStPresenter(view, new StudentDataService(activityCreateStudent),new ClassDataService(activityCreateStudent));
        database = new MySQLiteOpenHelper(activityCreateStudent).getWritableDatabase();
    }
    @Test
    public void shouldShowErrorMessageWhenFullNameisValid() throws Exception {
        when(view.getFullName()).thenReturn("");
        presenter.isCreateClicked();
        verify(view).showError(R.string.fullname_empty);
    }
}
