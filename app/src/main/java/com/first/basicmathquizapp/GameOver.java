package com.first.basicmathquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    TextView finalScore;
    Button restart, exit;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        finalScore = findViewById(R.id.textViewFInal);
        restart = findViewById(R.id.buttonRestart);
        exit = findViewById(R.id.buttonEnd);
        Intent intent = getIntent();
        score = intent.getIntExtra("point", 0);
        String fScore = String.valueOf(score);
        finalScore.setText("Final Score: "+fScore);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder build = new AlertDialog.Builder(GameOver.this);
                build.setTitle("Exit Quiz App");
                build.setMessage("Are you sure you want to exit the application?");
                build.setCancelable(false);
                build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        finish();
                    }
                });
                build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog = build.create();
                build.show();
            }
        });
    }
}