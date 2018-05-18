package com.icelabs.eeyan.bigman.volunteeapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import static com.icelabs.eeyan.bigman.volunteeapp.MainActivity.ITEM_MAP;

//TODO: Implement OnItemClickListener on RecyclerView
public class JobDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private JobItem jobItem;


    public JobDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {

            jobItem = ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);

            if (appBarLayout != null) {
                //TODO: Pass job title and description to fragment using loader
                //appBarLayout.setTitle(jobItem.getmJobTitle());
            }

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.job_detail, container, false);
        if (jobItem != null) {
            ((TextView) rootView.findViewById(R.id.job_detail)).setText(jobItem.getmJobDescription());
        }

        return rootView;
    }
}
