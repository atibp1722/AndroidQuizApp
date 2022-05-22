package com.first.basicmathquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameHome extends AppCompatActivity {
    TextView point, life, time, question;
    EditText answer;
    Button ok, next;
    //Random to generate numbers for questions
    Random random = new Random();
    int fNum, lNum, playerAnswer, correctAnswer;
    int pointScored = 0;
    int lifeRem = 3;
    //Countdown for time
    CountDownTimer countDownTimer;
    private static final long START_TIMER_IN_MILLIS = 60000;
    Boolean isTimeRunning;
    long timeRem = START_TIMER_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home);
        point = findViewById(R.id.textViewPoint);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);
        gameStart();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAnswer = Integer.valueOf(answer.getText().toString());
                pauseGameTime();
                if(playerAnswer==correctAnswer){
                    pointScored+=10;
                    point.setText(""+pointScored);
                    question.setText("Hurray! Answer is correct.");
                }else{
                    lifeRem-=1;
                    life.setText(""+lifeRem);
                    question.setText("Your answer is incorrect!");
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                gameStart();
                resetGameTime();
                if(lifeRem==0){
                    Toast.makeText(getApplicationContext(), "Game Over!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameHome.this, GameOver.class);
                    intent.putExtra("point", pointScored);
                    startActivity(intent);
                    finish();
                }else{
                    gameStart();
                }
            }
        });
    }
    //Logic of the game
    public void gameStart(){
        //Generate and assign random integer
        fNum = random.nextInt(99);
        lNum = random.nextInt(99);
        correctAnswer = fNum+lNum;
        /*correctAnswer = fNum-lNum;
        correctAnswer = fNum*lNum;
        correctAnswer = fNum/lNum;*/
        question.setText(fNum+ "+" +lNum);
        /*question.setText(fNum+ "-" +lNum);
        question.setText(fNum+ "*" +lNum);
        question.setText(fNum+ "/" +lNum);*/
        gameTime();
    }
    //Game timer logic
    public void gameTime(){
        countDownTimer = new CountDownTimer(timeRem, 1000) {
            @Override
            public void onTick(long l) {
                timeRem = l;
                updateTimeText();
            }

            @Override
            public void onFinish() {
                isTimeRunning=false;
                pauseGameTime();
                resetGameTime();
                updateTimeText();
                lifeRem-=1;
                life.setText(""+lifeRem);
                question.setText("Your time is up.");
            }
        }.start();
        isTimeRunning=true;
    }
    //Update time every second the player is active
    public void updateTimeText(){
        int second = (int)(timeRem/1000);
        String timeLeft = String.format(Locale.getDefault(),"%01d", second);
        time.setText(timeLeft);
    }
    //Reset time when player goes to next question
    public void resetGameTime(){
        timeRem = START_TIMER_IN_MILLIS;
        updateTimeText();
    }
    //Pause time when player enters answer
    public void pauseGameTime(){
        countDownTimer.cancel();
        isTimeRunning=false;
    }
}