package com.developerx.attendencemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void lecture(View view) {
        startActivity(new Intent(this, LectureDashboardActivity.class));
    }

    public void student(View view) {
        startActivity(new Intent(this, StudentDashboardActivity.class));
    }

    public void attendance(View view) {
        startActivity(new Intent(this, AttendanceDashboardActivity.class));
    }





}