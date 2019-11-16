package com.paket.userauthentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) { //Constructor
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(username text primary key,password text,email text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        String sql = "drop table if exists user";
        db.execSQL(sql);
        onCreate(db);
    }

    //Add user to table
    public boolean addUser(String s1,String s2,String s3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",s1);
        contentValues.put("password",s2);
        contentValues.put("email",s3);
        long result = db.insert("user",null,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //Login Page Control
    public String loginControl(String username,String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select username,password from user where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()> 0) {
            cursor.moveToFirst();
            String Username = cursor.getString(0);
            String Password = cursor.getString(1);
            cursor.close();
            return username;
        }return null;
    }

    //User Control(if exists user)
    public boolean userKontrol(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=?",new String[]{username});
        if (cursor.getCount()> 0) return false;
        else return true;
    }

    //Get access to all data
    public Cursor allData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user",null);
        return cursor;
    }

}
