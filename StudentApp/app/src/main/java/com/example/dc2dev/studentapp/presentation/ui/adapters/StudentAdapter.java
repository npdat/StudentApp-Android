package com.example.dc2dev.studentapp.presentation.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.data.clients.api.BitmapByte;
import com.example.dc2dev.studentapp.domain.entities.Student;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class StudentAdapter extends ArrayAdapter<Student> {

    Context context;
    public StudentAdapter(Context context, ArrayList<Student> users) {
        super(context, 0, users);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student st = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listviewstudent, parent, false);
        }

        ImageView img= (ImageView) convertView.findViewById(R.id.imgad);
        TextView txtname = (TextView) convertView.findViewById(R.id.txtnamead);
        TextView txtnameclass = (TextView) convertView.findViewById(R.id.txtnameclassad);

        if(st.getImage()!=null){
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(Uri.parse(st.getImage()),filePathColumn, null, null, null);
            if(cursor!=null){
                cursor.moveToFirst();
                cursor.close();
            }
            Bitmap bmp= BitmapByte.uritoBM(Uri.parse(st.getImage()),context);
            img.setImageBitmap(bmp);
        }
        txtname.setText(st.getFullname());
        txtnameclass.setText(st.getClassname());
        //{byte[0]4898};
        return convertView;
    }
}
