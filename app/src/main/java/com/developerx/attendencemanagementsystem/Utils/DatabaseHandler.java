package com.developerx.attendencemanagementsystem.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private  static final  int VERSION = 1;
    private  static final  String NAME = "attendance";

    private  static final  String CREATE_TABLE_STUDENT = "CREATE TABLE  student"+ "(" ;//todo workinh;

    private SQLiteDatabase db ;
    public DatabaseHandler(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void  openDatabase(){
        db= this.getWritableDatabase();
    }
    public  void  insertStudent(){

    }

}
