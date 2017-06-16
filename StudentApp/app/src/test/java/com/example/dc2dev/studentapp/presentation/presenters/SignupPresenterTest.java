package com.example.dc2dev.studentapp.presentation.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.MemberDataService;
import com.example.dc2dev.studentapp.domain.entities.Member;
import com.example.dc2dev.studentapp.presentation.ui.presenters.SignupPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.SignupView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    @Mock
    private MemberDataService dataservice;

    private String fullname = "mmmmmmmm";
    private String email = "m@gmail.com";
    private String passoword = "123456";
    private String cfpassoword = "123456";
    private int gender = 1;

    @Before
    public void setUp() throws Exception {
        dataservice = Mockito.mock(MemberDataService.class);
        presenter = new SignupPresenter(view, dataservice);
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
        when(view.getEmail()).thenReturn(email);
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

    @Test
    public void shouldStartMainActivityWhenSignUpSuccess() throws Exception {
        when(view.getFullName()).thenReturn(fullname);
        when(view.getEmail()).thenReturn(email);
        when(view.getPassword()).thenReturn(passoword);
        when(view.getCfPassword()).thenReturn(cfpassoword);
        when(view.getCbgender()).thenReturn(gender);
        when(dataservice.signup((Member) Mockito.anyObject())).thenReturn(true);
        presenter.onSignUpClicked();
        verify(view).navigationTolistst();
    }

}