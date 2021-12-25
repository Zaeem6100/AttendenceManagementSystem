package com.developerx.attendencemanagementsystem.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.developerx.attendencemanagementsystem.Model.Attendance;
import com.developerx.attendencemanagementsystem.Model.Student;
import com.developerx.attendencemanagementsystem.R;
import com.developerx.attendencemanagementsystem.Utils.DataSchema;

import java.util.List;

public class AttendanceSheetAdapter extends BaseAdapter {
    List<Student> students;
    Activity context;

    public AttendanceSheetAdapter(Activity applicationContext) {
        Student student = new Student(applicationContext);
        students = student.getStudents(applicationContext, null);
        context = applicationContext;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).get_id();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.item_attendance_caluculate_sheet, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        Student mStudent = students.get(i);
        viewHolder.name.setText(mStudent.getName());
        Attendance attendance = new Attendance();
        List<Attendance> mAttendances = attendance.getAttendance(context, DataSchema.Attendance.STUDENT_ID + "=" + mStudent.get_id());
        int absent = 0, present = 0;
        float total;
        final float percentage;

        for (Attendance mAttendance : mAttendances) {
            if (mAttendance.getMarkAttendance() == 0)
                absent++;
            present += mAttendance.getMarkAttendance();
        }

        total = mAttendances.size();
        percentage = (present / total) * 100f;

        viewHolder.present.setText(present + "");
        viewHolder.absent.setText(absent + "");
        viewHolder.total.setText(total + "");
        viewHolder.attendancePercentage.setText(Math.round(percentage * 100.0) / 100.0 + "%");
        viewHolder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_EMAIL, students.get(i).getEmailId());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Attendance");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi " + students.get(i).getName() + ", your attendance percentage is " + Math.round(percentage * 100.0) / 100.0 + "%");

                context.startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        return view;
    }

    class ViewHolder {
        TextView present, absent, total, attendancePercentage, name;
        Button send;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.name);
            present = (TextView) convertView.findViewById(R.id.present);
            absent = (TextView) convertView.findViewById(R.id.absent);
            total = (TextView) convertView.findViewById(R.id.totalHeader);
            attendancePercentage = (TextView) convertView.findViewById(R.id.attendancePercentHeader);
            send = (Button) convertView.findViewById(R.id.send);
        }
    }
}
