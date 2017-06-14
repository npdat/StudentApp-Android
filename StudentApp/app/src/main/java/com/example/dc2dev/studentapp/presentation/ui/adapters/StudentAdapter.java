package com.example.dc2dev.studentapp.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc2dev.studentapp.R;
import com.example.dc2dev.studentapp.domain.entities.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, ArrayList<Student> users) {
        super(context, 0, users);
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

        Picasso.with(getContext()).load(st.getImage()).into(img);
        txtname.setText(st.getFullname());
        txtnameclass.setText(st.getClassname());


        return convertView;
    }
}
