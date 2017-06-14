package com.example.dc2dev.studentapp.data.clients.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dc2dev.studentapp.data.clients.api.MySQLiteOpenHelper;
import com.example.dc2dev.studentapp.domain.entities.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc2dev on 6/9/17.
 */

public class TableMember {
    private SQLiteDatabase database;
    public static final String TEN_BANG = "Member";
    public static final String COT_MA = "id";
    public static final String COT_TEN = "fullname";
    public static final String COT_EMAIL = "email";
    public static final String COT_PASSWORD = "pass";
    public static final String COT_GIOITINH = "gender";
    public static final String TAO_BANG = "create table " + TEN_BANG
            + " ( " + COT_MA + " integer primary key autoincrement, "
            + COT_TEN + " text, " +COT_EMAIL + " text, "+COT_PASSWORD + " text, " + COT_GIOITINH + " integer);";

    public TableMember(Context context) {
        database = new MySQLiteOpenHelper(context).getWritableDatabase();
    }

    public long create(Member mb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COT_TEN, mb.getFullname());
        contentValues.put(COT_EMAIL, mb.getEmail());
        contentValues.put(COT_PASSWORD, mb.getPassword());
        contentValues.put(COT_GIOITINH, mb.getGender());
        return database.insert(TEN_BANG, null, contentValues);
    }

    public long delete(String id) {
        return database.delete(TEN_BANG, COT_MA + " = " + id, null);
    }

    public long update(Member mb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COT_TEN, mb.getFullname());
        contentValues.put(COT_EMAIL, mb.getEmail());
        contentValues.put(COT_PASSWORD, mb.getPassword());
        contentValues.put(COT_GIOITINH, mb.getGender());
        return database.update(TEN_BANG, contentValues, COT_MA + " = " + mb.getId(), null);
    }

    public List<Member> getListMember() {
        List<Member> members = new ArrayList<>();
        Cursor cursor = database.query(TEN_BANG, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                members.add(new Member(cursor.getInt(cursor.getColumnIndex(COT_MA)),
                        cursor.getString(cursor.getColumnIndex(COT_TEN)),
                        cursor.getString(cursor.getColumnIndex(COT_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(COT_PASSWORD)),
                        cursor.getInt(cursor.getColumnIndex(COT_GIOITINH))));
            }
            cursor.close();
        }
        return members;
    }

}

