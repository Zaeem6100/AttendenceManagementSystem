package com.developerx.attendencemanagementsystem.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.developerx.attendencemanagementsystem.Model.Lecture;
import com.developerx.attendencemanagementsystem.Model.Student;
import com.developerx.attendencemanagementsystem.R;
import com.developerx.attendencemanagementsystem.Utils.DataSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceListAdapter extends BaseAdapter {
    private Context mContext;
    List<Student> students;
    int lectureID;

    public AttendanceListAdapter(Context applicationContext, int lectureID) {
        mContext = applicationContext;
        this.lectureID = lectureID;
        Lecture lecture = new Lecture(applicationContext);
        List<Lecture> lectures = lecture.getLectures(applicationContext, DataSchema._ID + " = " + lectureID);
        Lecture mLecture = lectures.get(0);
        students = new ArrayList<>();
        String studentsWithCommaSeperate = mLecture.getSelectedStudents();
        //getting students  here....
        List<String> studentsList = Arrays.asList(studentsWithCommaSeperate.split(","));

        Student student = new Student(applicationContext);
        for (int i = 0; i < studentsList.size(); i++) {
            try {
                Student mStudent = student.getStudents(applicationContext, DataSchema._ID + "=" + Integer.parseInt(studentsList.get(i).toString())).get(0);
                students.add(mStudent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater;
        ViewHolder viewHolder;
        if (view == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_attendance_sheet, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(students.get(i).getName());
        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.present) {
                    //some code
                    students.get(i).setAbsentPresent(1);
                    students.get(i).setLectureID(lectureID);
                } else if (checkedId == R.id.absent) {
                    //some code
                    students.get(i).setAbsentPresent(0);
                    students.get(i).setLectureID(lectureID);
                }
            }
        });

        return  view;
    }


    public List<Student> getStudents() {
        return students;
    }

    public int getLectureID() {
        return lectureID;
    }

    static class ViewHolder {
        TextView name;
        RadioGroup radioGroup;
        Button save;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.name);
            radioGroup = (RadioGroup) convertView.findViewById(R.id.attendanceRG);

        }
    }
}
