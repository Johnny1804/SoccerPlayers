package com.example.soccerplayers.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soccerplayers.models.SoccerPlayer;
import com.example.soccerplayers.R;

public class MoreInfoActivity extends AppCompatActivity {
    private ImageView playerImageView = null;
    private TextView playerNameTextView = null;
    private TextView playerBirthTextView = null;
    private TextView playerPositionTextView = null;
    private TextView playerHeightTextView = null;
    private TextView playerCitizenshipTextView = null;
    private TextView playerCurrentClubTextView = null;
    private Button webInfoButton = null;

    private SoccerPlayer playerData = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        // wire objects with widgets
        playerImageView = findViewById(R.id.imageView);
        playerNameTextView = findViewById(R.id.nameView);
        playerBirthTextView = findViewById(R.id.birthView);
        playerPositionTextView= findViewById(R.id.positionView);
        playerHeightTextView = findViewById(R.id.heightView);
        playerCitizenshipTextView = findViewById(R.id.citizenshipView);
        playerCurrentClubTextView = findViewById(R.id.currentClubView);
        webInfoButton = findViewById(R.id.button2);

        // make data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            playerData = (SoccerPlayer) bundle.getSerializable("player");
        }

        // populate widgets with data
        if (playerData != null) {
            playerNameTextView.setText(playerData.getName());
            playerBirthTextView.setText(playerData.getBirth() + " (age " + playerData.getAge()+ ")");
            playerCurrentClubTextView.setText(playerData.getCurrentClub());
            playerCitizenshipTextView.setText(playerData.getCitizenship());
            playerHeightTextView.setText(playerData.getHeight());
            playerPositionTextView.setText(playerData.getPosition());

            String imageName = playerData.getImage();
            imageName = imageName.substring(0, imageName.indexOf("."));
            playerImageView.setImageResource(this.getResources().getIdentifier(imageName, "drawable", this.getPackageName()));
        }

        webInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // make an intent and a bundle
                Intent intent = new Intent(MoreInfoActivity.this, WebActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("url", playerData.getInfoURL());
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }
}