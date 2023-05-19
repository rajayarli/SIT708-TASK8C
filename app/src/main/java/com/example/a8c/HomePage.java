package com.example.a8c;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private EditText editText;
    private Button myPlaylistButton;
    private Button addToPlaylistButton;
    private Button playVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        editText = findViewById(R.id.editTextText2);
        myPlaylistButton = findViewById(R.id.button3);
        addToPlaylistButton = findViewById(R.id.button4);
        playVideoButton = findViewById(R.id.idBtnPlayVideo);

        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                Intent intent = new Intent(HomePage.this, Rlist.class);

                Rlist.addItemToList(url);
                Toast.makeText(HomePage.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Rlist.class);
                startActivity(intent);
            }
        });

        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                Intent intent = new Intent(HomePage.this, VideoPlayerActivity.class);
                if (!url.isEmpty()) {
                    intent.putExtra("url", url);
                }
                startActivity(intent);
            }
        });
    }
}
