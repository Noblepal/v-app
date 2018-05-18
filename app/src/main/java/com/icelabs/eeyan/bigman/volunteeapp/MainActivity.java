package com.icelabs.eeyan.bigman.volunteeapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Init git repo
public class MainActivity extends AppCompatActivity implements ItemClickListener {
    public ArrayList<JobItem> mJobsList;
    private RecyclerView mRecyclerView;
    private JobsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final Map<String, JobItem> ITEM_MAP = new HashMap<String, JobItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createDummyList();
        buildRecyclerView();
        setupSearch();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void createDummyList() {
        mJobsList = new ArrayList<>();

        addItem(new JobItem("1", R.drawable.landscape, "Sales Representative", "Advertise and sell our products in a specified area"));
        addItem(new JobItem("2", R.drawable.landscape, "Photographer", "Require skilled personnel to capture photos on our ventures"));
        addItem(new JobItem("3", R.drawable.landscape, "System Analyst", "Develop a documentation for our proprietary software"));
        addItem(new JobItem("4", R.drawable.landscape, "Human Resource Officer", "Manage our staff of over 200 employees"));
        addItem(new JobItem("5", R.drawable.landscape, "Internal Auditor", "Help us plan our budget and expenditure"));
        addItem(new JobItem("6", R.drawable.landscape, "System Administrator", "Control traffic on our website, send emails to clients"));
        addItem(new JobItem("7", R.drawable.landscape, "IT Consultant", "Help our company upgrade to digital data management"));
        addItem(new JobItem("8", R.drawable.landscape, "Food Inspector", "Assess food from 5-Star hotels in Ease Africa"));
        addItem(new JobItem("9", R.drawable.landscape, " Dummy Title To Test How The TextView Behaves Dummy Title To Test How The TextView Behaves", "Lots of dummy text to see whether the text view will expand and wrap text within it to fit Lots of dummy text to see whether the text view will expand and wrap text within it to fit Lots of dummy text to see whether the text view will expand and wrap text within it to fit"));

    }

    private void addItem(JobItem item) {
        mJobsList.add(item);
        //ITEM_MAP.put(item.mID, item);
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new JobsAdapter(mJobsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

    }

    private void setupSearch() {
        MaterialSearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processQuery(newText);
                return true;
            }
        });
    }

    public void processQuery(String query) {
        ArrayList<JobItem> filteredList = new ArrayList<>();

        for (JobItem item : mJobsList) {
            if (item.getmJobTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_menu);
        MaterialSearchView searchView = findViewById(R.id.searchView);
        searchView.setMenuItem(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {
        final JobItem item = mJobsList.get(position);
        Intent i = new Intent(this, JobDetailActivity.class);
        i.putExtra("Title", item.getmJobTitle());
        i.putExtra("Description", item.getmJobDescription());
        i.putExtra("Logo", item.getmImageResource());
        startActivity(i);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
