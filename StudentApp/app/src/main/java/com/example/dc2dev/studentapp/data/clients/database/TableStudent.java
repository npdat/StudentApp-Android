package com.example.dc2dev.studentapp.data.clients.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.domain.entities.Student;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class TableStudent{
        private SQLiteDatabase database;
        public static final String TEN_BANG = "Student";
        public static final String COT_MA = "id";
        public static final String COT_TEN = "fullname";
        public static final String COT_LOP = "classname";
        public static final String COT_HINH = "image";
        public static final String TAO_BANG = "create table " + TEN_BANG
                + " ( " + COT_MA + " integer primary key autoincrement, "
                + COT_TEN + " text, " +COT_LOP + " text, " + COT_HINH + " text);";

        public TableStudent(Context context) {
            database = new MySQLiteOpenHelper(context).getWritableDatabase();

        }

        public long create(Student st) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COT_TEN, st.getFullname());
            contentValues.put(COT_LOP, st.getClassname());
            contentValues.put(COT_HINH, st.getImage());
            return database.insert(TEN_BANG, null, contentValues);
        }

        public long delete(String id) {
            return database.delete(TEN_BANG, COT_MA + " = " + id, null);
//        return database.delete(TEN_BANG, "id = ?", new String[] {ma});
        }

        public long update(Student st) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COT_TEN, st.getFullname());
            contentValues.put(COT_LOP, st.getClassname());
            contentValues.put(COT_HINH, st.getImage());
            return database.update(TEN_BANG, contentValues, COT_MA + " = " + st.getId(), null);
        }

        public ArrayList<Student> getListStudent() {
            ArrayList<Student> students = new ArrayList<>();
            Cursor cursor = database.query(TEN_BANG, null, null, null, null, null, null);
            if (cursor != null) {
                String st = "";
                while (cursor.moveToNext()) {
                    students.add(new Student(cursor.getInt(cursor.getColumnIndex(COT_MA)),
                            cursor.getString(cursor.getColumnIndex(COT_TEN)),
                            cursor.getString(cursor.getColumnIndex(COT_LOP)),
                            cursor.getString(cursor.getColumnIndex(COT_HINH))));
                }
                cursor.close();
            }
            return students;
        }


}

