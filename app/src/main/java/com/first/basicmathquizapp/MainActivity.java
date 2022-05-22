package com.first.basicmathquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Buttons for actions
    Button add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnSub);
        mul = findViewById(R.id.btnMul);
        div = findViewById(R.id.btnDiv);
        //Listeners for handling operation for specific button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent to redirect to another activity
                Intent intent =  new Intent(MainActivity.this, GameHome.class);
                startActivity(intent);
                finish();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent =  new Intent(MainActivity.this, GameHome.class);
                startActivity(intent);
                finish();*/
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent =  new Intent(MainActivity.this, GameHome.class);
                startActivity(intent);
                finish();*/
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent =  new Intent(MainActivity.this, GameHome.class);
                startActivity(intent);
                finish();*/
            }
        });
    }
}