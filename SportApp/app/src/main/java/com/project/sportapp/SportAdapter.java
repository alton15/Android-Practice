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
        return new ViewHolder( itemView, this );
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
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleView;
        private TextView mInfoView;
        final SportAdapter mAdapter;

        public ViewHolder(@NonNull View itemView, SportAdapter adapter) {
            super(itemView);
            this.mTitleView = itemView.findViewById(R.id.title);
            this.mInfoView = itemView.findViewById(R.id.subTitle);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            mTitleView.setText(currentSport.getTitle());
            mInfoView.setText(currentSport.getInfo());
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Sport item = mSportsData.get(mPosition);
            String element = item.getInfo();
            item.setInfo("Clicked " + element);
            mAdapter.notifyDataSetChanged();
        }
    }
}
