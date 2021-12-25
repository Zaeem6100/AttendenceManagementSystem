package com.developerx.attendencemanagementsystem.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developerx.attendencemanagementsystem.Utils.*;

import java.util.ArrayList;
import java.util.List;

public class Attendance{

    public Attendance() {
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private int _id;


    public Attendance(int markAttendance, int lectureID, int studentID) {
        this.markAttendance = markAttendance;
        this.lectureID = lectureID;
        this.studentID = studentID;

    }
    public void addAttendance(Context context) {
        DbUtil dbUtils = new DbUtil(context);
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        ContentValues cv = new ContentValues();
        cv.put(DataSchema.Attendance.LECTURE_ID, lectureID);
        cv.put(DataSchema.Attendance.MARK_ATTENDANCE, markAttendance);
        cv.put(DataSchema.Attendance.STUDENT_ID, studentID);
        sqLiteDatabase.insert(DataSchema.Attendance.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Attendance> getAttendance(Context context, String where) {
        List<Attendance> attendances = null;
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        Cursor cursor = sqLiteDatabase.query(DataSchema.Attendance.TABLE_NAME, null, where, null, null, null, null);
        if (cursor != null)
            attendances = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") Attendance attendance = new Attendance(
                        cursor.getInt(cursor.getColumnIndex(DataSchema.Attendance.MARK_ATTENDANCE)),
                        cursor.getInt(cursor.getColumnIndex(DataSchema.Attendance.LECTURE_ID)),
                        cursor.getInt(cursor.getColumnIndex(DataSchema.Attendance.STUDENT_ID)));
                attendance.set_id(cursor.getInt(cursor.getColumnIndex(DataSchema._ID)));

                attendances.add(attendance);
            } while (cursor.moveToNext());
        }

        return attendances;
    }


    private int markAttendance;
    private int lectureID;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public int getMarkAttendance() {
        return markAttendance;
    }

    public void setMarkAttendance(int markAttendance) {
        this.markAttendance = markAttendance;
    }


    private int studentID;
}
