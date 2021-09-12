package com.example.soccerplayers.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerplayers.adapters.HistoryRecyclerViewAdapter;
import com.example.soccerplayers.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    // declare objects
    private RecyclerView recyclerView = null;
    private HistoryRecyclerViewAdapter mAdapter = null;
    private RecyclerView.LayoutManager layoutManager = null;

    private List <String> history = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // make data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            history = (List <String>) bundle.getStringArrayList("history");
        }

        // prepare objects
        recyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);


        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new HistoryRecyclerViewAdapter(this, history);
        recyclerView.setAdapter(mAdapter);
    }
}