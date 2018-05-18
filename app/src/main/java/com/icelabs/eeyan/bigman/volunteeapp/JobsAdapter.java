package com.icelabs.eeyan.bigman.volunteeapp;

import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    private ArrayList<JobItem> mJobsList;
    private ItemClickListener clickListener;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageView;
        public TextView mJobTitle;
        public TextView mJobDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.job_logo);
            mJobTitle = itemView.findViewById(R.id.job_title);
            mJobDescription = itemView.findViewById(R.id.job_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            JobItem item = (JobItem) view.getTag();

            Context context = view.getContext();
            Intent intent = new Intent(context, JobDetailActivity.class);
            //intent.putExtra(JobDetailFragment.ARG_ITEM_ID, item.mID);

            context.startActivity(intent);
        }
    };

    public JobsAdapter(ArrayList<JobItem> jobsList) {
        mJobsList = jobsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JobItem currentJobItem = mJobsList.get(position);

        holder.mImageView.setImageResource(currentJobItem.getmImageResource());
        holder.mJobTitle.setText(currentJobItem.getmJobTitle());
        holder.mJobDescription.setText(currentJobItem.getmJobDescription());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mJobsList.size();
    }

    public void filterList(ArrayList<JobItem> filteredList) {
        mJobsList = filteredList;
        notifyDataSetChanged();
    }

}