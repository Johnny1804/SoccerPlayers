package com.example.soccerplayers.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerplayers.adapters.PlayerRecyclerViewAdapter;
import com.example.soccerplayers.models.XMLData;
import com.example.soccerplayers.R;

public class MainActivity extends AppCompatActivity {

    private XMLData xmlData = null;

    private RecyclerView recyclerView = null;
    private PlayerRecyclerViewAdapter mAdapter = null;
    private RecyclerView.LayoutManager layoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.player_recycler_view);

        xmlData = new XMLData(this.getApplicationContext());

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new PlayerRecyclerViewAdapter(this, xmlData.getPlayers());
        recyclerView.setAdapter(mAdapter);
    }
}