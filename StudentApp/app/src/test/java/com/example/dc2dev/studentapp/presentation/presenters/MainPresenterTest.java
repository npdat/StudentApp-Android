package com.example.dc2dev.studentapp.presentation.presenters;

import com.example.dc2dev.studentapp.domain.entities.service.StudentService;
import com.example.dc2dev.studentapp.presentation.ui.presenters.MainPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private StudentService dataservice;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(view, dataservice);
        dataservice = Mockito.mock(StudentService.class);
    }
    @Test
    public void CheckIntenttoCreate(){
        presenter.onCreateClicked();
        verify(view).intenttocreatest();
    }
    @Test
    public void CheckIntenttoUpdate(){
        presenter.oUpdateClicked(Mockito.anyInt());
        verify(view).intenttoupdatest(Mockito.anyInt());
    }
}
