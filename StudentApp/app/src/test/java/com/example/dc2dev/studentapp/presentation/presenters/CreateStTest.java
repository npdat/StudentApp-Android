package com.example.dc2dev.studentapp.presentation.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.presentation.ui.presenters.CreateStPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.CreateStView;

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
public class CreateStTest {

    @Mock
    private CreateStView view;
    @Mock
    private CreateStPresenter presenter;
    @Mock
    private StudentDataService studentService;
    @Mock
    private ClassDataService classService;

    @Before
    public void setUp() throws Exception {
        studentService = Mockito.mock(StudentDataService.class);
        classService = Mockito.mock(ClassDataService.class);
        presenter = new CreateStPresenter(view, studentService, classService);
    }

    @Test
    public void shouldShowErrorMessageWhenFullNameisValid() throws Exception {
        when(view.getFullName()).thenReturn("");
        presenter.isCreateClicked();
        verify(view).showError(R.string.fullname_empty);
    }
}
