package com.example.dc2dev.studentapp.presentation.presenters;

import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.presentation.ui.activities.ActivitySignUp;
import com.example.dc2dev.studentapp.presentation.ui.presenters.SignupPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.SignupView;

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
public class SignupPresenterTest {
    @Mock
    private SignupView view;
    @Mock
    private SignupPresenter presenter;
    private ActivitySignUp activitySignUp;
    private SQLiteDatabase database;

    private String emailInValid = "m";
    private String emailValid = "npdat@tma.com.vn";
    private String passoword = "123456";

    @Before
    public void setUp() throws Exception {
        activitySignUp = new ActivitySignUp();
        presenter = new SignupPresenter(view, new MemberDataService(activitySignUp));
        database = new MySQLiteOpenHelper(activitySignUp).getWritableDatabase();
        //LoginPresenter loginPresenter = Mockito.mock(LoginPresenter.class);
        //Mockito.when(loginPresenter.onLoginClicked()).thenReturn(true);
    }

    @Test
    public void shouldShowErrorMessageWhenFullNameisValid() throws Exception {
        when(view.getFullName()).thenReturn("adsd");
        presenter.onSignUpClicked();
        verify(view).showError(R.string.fullname_invalid);
    }
    @Test
    public void shouldShowErrorMessageWhenEmailIsInvalid() throws Exception {
        when(view.getFullName()).thenReturn("mmmmmm");
        when(view.getEmail()).thenReturn(emailInValid);
        presenter.onSignUpClicked();
        verify(view).showError(R.string.email_invalid);
    }
    @Test
    public void shouldShowErrorMessageWhenPasswordEmpty() throws Exception {
        when(view.getFullName()).thenReturn("mmmmmm");
        when(view.getEmail()).thenReturn("m@gmail.com");
        when(view.getPassword()).thenReturn("");
        presenter.onSignUpClicked();
        verify(view).showError(R.string.password_empty);
    }
    @Test
    public void shouldShowErrorMessageWhenCfPasswordEmpty() throws Exception {
        when(view.getFullName()).thenReturn("mmmmmm");
        when(view.getEmail()).thenReturn("m@gmail.com");
        when(view.getPassword()).thenReturn("123");
        when(view.getCfPassword()).thenReturn("");
        presenter.onSignUpClicked();
        verify(view).showError(R.string.cfpassword_empty);
    }

    @Test
    public void shouldShowErrorMessageWhenCfPasswordInvalid() throws Exception {
        when(view.getFullName()).thenReturn("mmmmmm");
        when(view.getEmail()).thenReturn("m@gmail.com");
        when(view.getPassword()).thenReturn("123");
        when(view.getCfPassword()).thenReturn("1233");
        when(view.getCbgender()).thenReturn(0);
        presenter.onSignUpClicked();
        verify(view).showError(R.string.cfpassword_invalid);
    }

//    @Test
//    public void shouldStartMainActivityWhenSignUpSuccess() throws Exception {
//        presenter.onSignUpClicked();
//        verify(view).showError();
//    }
}