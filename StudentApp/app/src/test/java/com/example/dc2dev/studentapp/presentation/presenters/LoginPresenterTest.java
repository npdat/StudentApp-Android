package com.example.dc2dev.studentapp.presentation.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.presentation.ui.presenters.LoginPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by npdat on 6/15/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginView view;
    @Mock
    private LoginPresenter presenter;
    @Mock
    private MemberDataService dataservice;

    private String emailInValid = "npdat@tma";
    private String emailValid = "npdat@tma.com.vn";
    private String passoword = "123456";

    @Before
    public void setUp() throws Exception {
        dataservice = Mockito.mock(MemberDataService.class);
        presenter = new LoginPresenter(view, dataservice);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        when(view.getEmail()).thenReturn("");
        presenter.onLoginClicked();
        verify(view).showEmailError(R.string.email_empty);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsInvalid() throws Exception {
        when(view.getEmail()).thenReturn(emailInValid);
        presenter.onLoginClicked();
        verify(view).showEmailError(R.string.email_invalid);
    }

    @Test
    public void shouldStartMainActivityWhenEmailAndPasswordAreCorrect() throws Exception {
        when(view.getEmail()).thenReturn(emailValid);
        when(view.getPassword()).thenReturn(passoword);
        when(dataservice.login(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        presenter.onLoginClicked();
        verify(view).navigationToHome();
    }
    @Test
    public void shouldShowErrorMessageWhenEmailAndPasswordAreNotCorrect() throws Exception {
        when(view.getEmail()).thenReturn(emailValid);
        when(view.getPassword()).thenReturn(passoword);
        when(dataservice.login(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        presenter.onLoginClicked();
        verify(view).loginFail();
    }
}
