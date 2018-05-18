package com.icelabs.eeyan.bigman.volunteeapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobItem {

    public final String mID;

    private final int mImageResource;
    private final String mJobTitle;
    private final String mJobDescription;


    public JobItem(String id, int imageResource, String jobTitle, String jobDescription) {
        mID = id;
        mImageResource = imageResource;
        mJobTitle = jobTitle;
        mJobDescription = jobDescription;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmJobTitle() {
        return mJobTitle;
    }

    public String getmJobDescription() {
        return mJobDescription;
    }
}
