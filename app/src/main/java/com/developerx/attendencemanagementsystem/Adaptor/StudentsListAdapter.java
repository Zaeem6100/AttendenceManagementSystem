package com.developerx.attendencemanagementsystem.Adaptor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.developerx.attendencemanagementsystem.Model.Student;
import com.developerx.attendencemanagementsystem.R;
import com.developerx.attendencemanagementsystem.Utils.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentsListAdapter extends BaseAdapter {
    List<Student> students;
    Context mContext;
    String from;

    public StudentsListAdapter(Context context) {
        mContext = context;
        getData();

    }

    public StudentsListAdapter(Context context, String dialog) {
        mContext = context;
        getData();
        from = dialog;
    }

    void getData() {
        SQLiteDatabase sqLiteDatabase = DbUtil.sqLiteDatabase;
        Student student = new Student(mContext);
        students = new ArrayList<>();
        students = student.getStudents(mContext, null);
        sqLiteDatabase.close();
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
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_student, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (from == null) {
            viewHolder.name.setText("Name:" + students.get(i).getName());
            viewHolder.tNumber.setText("Trojan number:" + students.get(i).gettNumber());
            viewHolder.nameCB.setVisibility(View.GONE);
        } else {
            viewHolder.nameCB.setVisibility(View.VISIBLE);
            viewHolder.nameCB.setText(students.get(i).getName());
            viewHolder.name.setVisibility(View.GONE);
            viewHolder.tNumber.setVisibility(View.GONE);
        }
        return view;
    }
    public void updateData() {
        getData();
        notifyDataSetChanged();
    }
    class ViewHolder {
        TextView name, tNumber;
        CheckBox nameCB;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.name);
            tNumber = (TextView) v.findViewById(R.id.tNumber);
            nameCB = (CheckBox) v.findViewById(R.id.nameCB);
        }

    }
}
