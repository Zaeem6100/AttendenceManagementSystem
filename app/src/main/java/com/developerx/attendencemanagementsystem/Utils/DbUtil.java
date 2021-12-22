package com.developerx.attendencemanagementsystem.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbUtil {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static String DB_NAME = "attendance";
    private int DB_VERSION = 1;
    private  static  final  String CREATE_TABLE_STUDENT = "CREATE TABLE IF NOT EXISTS " + DataSchema.Student.TABLE_NAME + "(" + DataSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataSchema.Student.NAME +
            " TEXT," + DataSchema.Student.TNUMBER + " TEXT," + DataSchema.Student.PHONE_NUMBER + " TEXT,"
            + DataSchema.Student.EMAIL_ID
            + " TEXT);";

    private  static  final  String CREATE_TABLE_LECTURE ="CREATE TABLE IF NOT EXISTS " + DataSchema.Lecture.TABLE_NAME + "(" + DataSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataSchema.Lecture.COURSE_NAME +
            " TEXT," + DataSchema.Lecture.COURSE_DETAILS + " TEXT," + DataSchema.Lecture.CLASS_CODE
            + " text," + DataSchema.Lecture.START_DATE_TIME + " text," + DataSchema.Lecture.END_DATE_TIME + " text,"
            + DataSchema.Lecture.SELECTED_STUDENTS + " text);";

    private  static  final  String CREATE_TABLE_ATTENDANCE = "CREATE TABLE IF NOT EXISTS " + DataSchema.Attendance.TABLE_NAME + "(" + DataSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataSchema.Attendance.LECTURE_ID +
            " INTEGER," + DataSchema.Attendance.MARK_ATTENDANCE + " INTEGER," + DataSchema.Attendance.STUDENT_ID
            + " INTEGER);";


    public DbUtil(Context context) {

        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);

    }

    public  SQLiteDatabase open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase;
    }

    public  void close() {
        sqLiteDatabase.close();
    }



    public  class  DBHelper extends SQLiteOpenHelper{



        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
            sqLiteDatabase.execSQL(CREATE_TABLE_LECTURE);
            sqLiteDatabase.execSQL(CREATE_TABLE_ATTENDANCE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataSchema.Users.TABLE_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataSchema.Student.TABLE_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataSchema.Lecture.TABLE_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataSchema.Attendance.TABLE_NAME + ";");
        }
    }
}
