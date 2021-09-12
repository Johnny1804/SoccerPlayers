package com.example.soccerplayers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soccerplayers.models.SoccerPlayer;
import com.example.soccerplayers.R;

public class PlayerActivity extends AppCompatActivity {

    private ImageView playerImageView = null;
    private TextView playerNameTextView = null;
    private TextView playerAgeTextView = null;
    private TextView playerCurrentClubTextView = null;
    private Button infoButton = null;

    private SoccerPlayer playerData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // wire objects with widgets
        playerImageView = findViewById(R.id.imageView);
        playerNameTextView = findViewById(R.id.nameView);
        playerAgeTextView = findViewById(R.id.ageView);
        playerCurrentClubTextView = findViewById(R.id.currentClubView);
        infoButton = findViewById(R.id.button);

        // make data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            playerData = (SoccerPlayer) bundle.getSerializable("player");
        }

        // populate widgets with data
        if (playerData != null) {
            playerNameTextView.setText(playerData.getName());
            playerAgeTextView.setText(playerAgeTextView.getText().toString() + " " + playerData.getAge());
            playerCurrentClubTextView.setText(playerData.getCurrentClub());

            String imageName = playerData.getImage();
            imageName = imageName.substring(0, imageName.indexOf("."));
            playerImageView.setImageResource(this.getResources().getIdentifier(imageName, "drawable", this.getPackageName()));
        }

        // deal with button events
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // make an intent and a bundle
                Intent intent = new Intent(PlayerActivity.this, MoreInfoActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("player", playerData);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });


    }
}