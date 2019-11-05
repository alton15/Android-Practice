package com.project.sportapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private ArrayList<Sport> mSportsData;
    private Context mContext;

    public SportAdapter(ArrayList<Sport> mSportsData, Context mContext) {
        this.mSportsData = mSportsData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.ViewHolder holder, int position) {
        Sport currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitleView;
        private TextView mInfoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTitleView = itemView.findViewById(R.id.title);
            this.mInfoView = itemView.findViewById(R.id.subTitle);
        }

        void bindTo(Sport currentSport) {
            mTitleView.setText(currentSport.getTitle());
            mInfoView.setText(currentSport.getInfo());
        }
    }
}
