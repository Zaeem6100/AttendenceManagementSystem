package com.developerx.attendencemanagementsystem.Adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developerx.attendencemanagementsystem.R;
import com.developerx.attendencemanagementsystem.RealmDatabase.Class_Names;

import io.realm.RealmChangeListener;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.Realm;

public class ClassListAdapter extends RealmRecyclerViewAdapter<Class_Names, RecyclerView.ViewHolder> {

    private final Activity mActivity;
    RealmResults<Class_Names> mList;

    Realm realm;
    RealmChangeListener realmChangeListener;


    public ClassListAdapter(RealmResults<Class_Names> list, Activity context) {

        super(context, list, true);
        realm = Realm.getDefaultInstance();
        mActivity = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_adapter, parent, false);
        return new ViewHolder(itemView, mActivity, mList);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
    //todo
}
