package com.developerx.attendencemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.developerx.attendencemanagementsystem.Adaptor.AttendanceListAdapter;
import com.developerx.attendencemanagementsystem.Adaptor.LectureListAdapter;
import com.developerx.attendencemanagementsystem.Model.Attendance;
import com.developerx.attendencemanagementsystem.Model.Student;

import java.util.List;

public class AttendanceDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_dashboard);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ListView attendanceListView = (ListView) findViewById(R.id.attendanceListView);
        TextView totalHeader, attendancePercentHeader;
        totalHeader = (TextView) findViewById(R.id.totalHeader);
        attendancePercentHeader = (TextView) findViewById(R.id.attendancePercentHeader);
        Button save = (Button) findViewById(R.id.save);
        int lectureID = 0;
        if (intent.hasExtra(LectureListAdapter.LECTURE_ID))
            lectureID = intent.getIntExtra(LectureListAdapter.LECTURE_ID, 0);
        if (lectureID > 0) {

            totalHeader.setVisibility(View.GONE);
            attendancePercentHeader.setVisibility(View.GONE);
            final AttendanceListAdapter attendanceListAdapter = new AttendanceListAdapter(getApplicationContext(), lectureID);
            attendanceListView.setAdapter(attendanceListAdapter);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // save to db and calculate attendance percentage,,,,,
                    List<Student> students = attendanceListAdapter.getStudents();
                    for (Student student : students) {
                        Attendance attendance = new Attendance(student.getAbsentPresent(), student.getLectureID(), student.get_id());
                        attendance.addAttendance(getApplicationContext());
                    }


                    finish();
                }
            });
        }

        }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}