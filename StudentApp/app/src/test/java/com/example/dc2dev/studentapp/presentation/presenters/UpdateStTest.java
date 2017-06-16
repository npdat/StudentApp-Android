package com.example.dc2dev.studentapp.presentation.presenters;

import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.presentation.ui.activities.ActivityUpdateStudent;
import com.example.dc2dev.studentapp.presentation.ui.presenters.UpdateStPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.UpdateStView;

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
public class UpdateStTest {
    @Mock
    private UpdateStView view;
    @Mock
    private UpdateStPresenter presenter;
    private ActivityUpdateStudent updateStudent;
    private SQLiteDatabase database;

    @Before
    public void setUp() throws Exception {
        updateStudent = new ActivityUpdateStudent();
        presenter = new UpdateStPresenter(view, new StudentDataService(updateStudent),new ClassDataService(updateStudent));
        database = new MySQLiteOpenHelper(updateStudent).getWritableDatabase();
    }
    @Test
    public void shouldShowErrorMessageWhenFullNameisValid() throws Exception {
        when(view.getFullName()).thenReturn("");
        presenter.isUpdateClicked();
        verify(view).showError(R.string.fullname_empty);
    }
}
