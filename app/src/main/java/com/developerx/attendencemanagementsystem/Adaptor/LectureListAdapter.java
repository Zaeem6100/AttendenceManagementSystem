package com.developerx.attendencemanagementsystem.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.developerx.attendencemanagementsystem.AttendanceDashboardActivity;
import com.developerx.attendencemanagementsystem.Model.Lecture;
import com.developerx.attendencemanagementsystem.R;
import com.developerx.attendencemanagementsystem.Utils.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class LectureListAdapter  extends BaseAdapter {
    public static final String LECTURE_ID = "lecture_id";
    List<Lecture> lectures;
    Activity mContext;


    public LectureListAdapter(Activity applicationContext) {
        this.mContext = applicationContext;
        getData();
    }

    private void getData() {

        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        Lecture lecture = new Lecture(mContext);
        lectures = new ArrayList<>();
        lectures = lecture.getLectures(mContext, null);
        sqLiteDatabase.close();
    }

    @Override
    public int getCount() {
        return lectures.size();
    }

    @Override
    public Object getItem(int i) {
        return lectures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lectures.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {

            view = layoutInflater.inflate(R.layout.item_lecture, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.courseName.setText("Class:" + lectures.get(i).getCourseName());
        viewHolder.takeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to attendance activity
                Intent intent = new Intent(mContext, AttendanceDashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(LECTURE_ID, lectures.get(i).getId());
                mContext.startActivity(intent);
                mContext.finish();
            }
        });
        return view;
    }
    public void updateData() {
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        Lecture lecture = new Lecture(mContext);
        lectures = new ArrayList<>();
        lectures = lecture.getLectures(mContext, null);
       sqLiteDatabase.close();
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView courseName, courseDetails;
        Button takeAttendance;

        public ViewHolder(View v) {
            courseName = (TextView) v.findViewById(R.id.courseName);
            courseDetails = (TextView) v.findViewById(R.id.courseDetails);
            takeAttendance = (Button) v.findViewById(R.id.takeAttendance);
        }

    }
}
