package com.developerx.attendencemanagementsystem.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developerx.attendencemanagementsystem.Utils.DataSchema;
import com.developerx.attendencemanagementsystem.Utils.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String courseName;
    private String courceDetails;
    private String classCode;


    public String getCourceDetails() {
        return courceDetails;
    }

    public String getCourseName() {
        return courseName;
    }

    private String startDateTime;

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    private String endDateTime;

    public String getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(String selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public void setCourceDetails(String courceDetails) {
        this.courceDetails = courceDetails;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String selectedStudents;

    public Lecture(String courseName, String courceDetails, String classCode, String startDateTime, String endDateTime,String selectedStudents) {
        this.courseName = courseName;
        this.courceDetails = courceDetails;
        this.classCode = classCode;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.selectedStudents=selectedStudents;
    }


    public Lecture(Context mContext) {


    }

    public void addLectue(Context context) {
        DbUtil dbUtils = new DbUtil(context);
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        ContentValues cv = new ContentValues();
        cv.put(DataSchema.Lecture.COURSE_NAME, courseName);
        cv.put(DataSchema.Lecture.COURSE_DETAILS, courceDetails);
        cv.put(DataSchema.Lecture.CLASS_CODE, classCode);
        cv.put(DataSchema.Lecture.START_DATE_TIME, startDateTime);
        cv.put(DataSchema.Lecture.END_DATE_TIME, endDateTime);
        cv.put(DataSchema.Lecture.SELECTED_STUDENTS,selectedStudents);

        sqLiteDatabase.insert(DataSchema.Lecture.TABLE_NAME, null, cv);

    }


    @SuppressLint("Range")
    public List<Lecture> getLectures(Context mContext, String where) {
        List<Lecture> lectures = null;
        DbUtil dbUtils = new DbUtil(mContext);
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        Cursor cursor = sqLiteDatabase.query(DataSchema.Lecture.TABLE_NAME, null, where, null, null, null, null);
        if (cursor != null)
            lectures = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") Lecture lecture = new Lecture(cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.COURSE_NAME)),
                        cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.COURSE_DETAILS)),
                        cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.CLASS_CODE)),
                        cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.START_DATE_TIME)),
                        cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.END_DATE_TIME)),
                        cursor.getString(cursor.getColumnIndex(DataSchema.Lecture.SELECTED_STUDENTS)));


                lecture.setId(cursor.getInt(cursor.getColumnIndex(DataSchema._ID)));
                lectures.add(lecture);
            } while (cursor.moveToNext());
        }

        return lectures;
    }
}
