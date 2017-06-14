package com.example.dc2dev.studentapp.data.clients.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.domain.entities.Class;

import java.util.ArrayList;

/**
 * Created by dc2dev on 6/9/17.
 */

public class TableClass {
    private SQLiteDatabase database;
    public static final String TEN_BANG = "Class";
    public static final String COT_MA = "id";
    public static final String COT_TEN = "classname";
    public static final String TAO_BANG = "create table " + TEN_BANG
            + " ( " + COT_MA + " integer primary key autoincrement, " + COT_TEN + " text);";

    public TableClass(Context context) {
        database = new MySQLiteOpenHelper(context).getWritableDatabase();

    }

    public long create(Class cl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COT_TEN, cl.getName());
        return database.insert(TEN_BANG, null, contentValues);
    }

    public long delete(String id) {
        return database.delete(TEN_BANG, COT_MA + " = " + id, null);
    }

    public long update(Class cl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COT_TEN, cl.getName());
        return database.update(TEN_BANG, contentValues, COT_MA + " = " + cl.getId(), null);
    }

    public ArrayList<Class> getListClass() {
        ArrayList<Class> classes = new ArrayList<>();
        Cursor cursor = database.query(TEN_BANG, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                classes.add(new Class(cursor.getInt(cursor.getColumnIndex(COT_MA)),
                        cursor.getString(cursor.getColumnIndex(COT_TEN))));
            }
            cursor.close();
        }
        return classes;
    }
}
