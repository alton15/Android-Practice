package com.project.sportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        mSportsData = new ArrayList<>();

        mAdapter = new SportAdapter(mSportsData,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();
    }

    private void initData() {

        mSportsData.clear();

        String[] sportsList = getResources().getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);

        for(int i = 0; i < sportsList.length; i++) {
        mSportsData.add(new Sport(sportsList[i], sportsInfo[i]));
        }

        mAdapter.notifyDataSetChanged();
    }

}
