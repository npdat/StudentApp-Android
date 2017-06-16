package com.example.dc2dev.studentapp.presentation.presenters;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.service.ClassDataService;
import com.example.dc2dev.studentapp.data.clients.service.StudentDataService;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.example.dc2dev.studentapp.presentation.ui.presenters.UpdateStPresenter;
import com.example.dc2dev.studentapp.presentation.ui.views.UpdateStView;

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
public class UpdateStTest {
    @Mock
    private UpdateStView view;
    @Mock
    private UpdateStPresenter presenter;
    @Mock
    private StudentDataService studentService;
    @Mock
    private ClassDataService classService;
    private String name = "minhhhhh";
    private String clas = "m@gmail.com";
    private String img = "content://com.android.providers.media.documents/document/image%3A81";
    private String imgnull = "content://com.android.providers.media.documents/document/image%3A81";

    Student studentu=new Student(12,"minh","Lop 1","content://com.android.providers.media.documents/document/image%3A81");

    @Before
    public void setUp() throws Exception {
        studentService = Mockito.mock(StudentDataService.class);
        classService = Mockito.mock(ClassDataService.class);
        presenter = new UpdateStPresenter(view, studentService, classService);
    }
    @Test
    public void shouldShowErrorMessageWhenFullNameisValid() throws Exception {
        when(view.getFullName()).thenReturn("");
        presenter.isUpdateClicked();
        verify(view).showError(R.string.fullname_empty);
    }
    @Test
    public void shouldShowErrorMessageWhenUpdateSuccsessWithImgNotNull() throws Exception {
        when(view.getFullName()).thenReturn(name);
        when(view.getClas()).thenReturn(clas);
        when(view.getImg()).thenReturn(img);
        when(studentService.createst((Student) Mockito.anyObject())).thenReturn(true);
        presenter.isUpdateClicked();
        verify(view).updatesusscess();
    }
    @Test
    public void shouldShowErrorMessageWhenUpdateSuccsessWithImgNull() throws Exception {
        when(view.getFullName()).thenReturn(name);
        when(view.getClas()).thenReturn(clas);
        when(view.getImg()).thenReturn(imgnull);
        when(studentService.createst((Student) Mockito.anyObject())).thenReturn(true);
        presenter.isUpdateClicked();
        verify(view).updatesusscess();
    }
}
