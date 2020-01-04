package com.example.bbbtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView weekTextView;

    TextView pressTextView;
    TextView deadTextView;
    TextView benchTextView;
    TextView squatTextView;

    String weekVal;
    String pressVal;
    String deadVal;
    String benchVal;
    String squatVal;

    Button pressButton;
    Button deadButton;
    Button benchButton;
    Button squatButton;
    Button optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViews();

        initButtons();

    }

    private void initTextViews(){

        // Identify text views
        weekTextView = findViewById(R.id.weekValText);
        pressTextView = findViewById(R.id.pressValText);
        deadTextView = findViewById(R.id.deadValText);
        benchTextView = findViewById(R.id.benchValText);
        squatTextView = findViewById(R.id.squatValText);

        // Pull values from SharedPrefs
        initSharedPrefs();

        // Set content
        weekTextView.setText(weekVal);
        pressTextView.setText(pressVal);
        deadTextView.setText(deadVal);
        benchTextView.setText(benchVal);
        squatTextView.setText(squatVal);

    }

    private void initSharedPrefs(){
        // Todo: init shared pref values and place into strings

        weekVal = "1";

        pressVal = "125";
        deadVal = "315";
        benchVal = "225";
        squatVal = "225";

    }

    private void initButtons(){

        pressButton = findViewById(R.id.pressButton);
        deadButton = findViewById(R.id.deadButton);
        benchButton = findViewById(R.id.benchButton);
        squatButton = findViewById(R.id.squatButton);
        optionsButton = findViewById(R.id.optionsButton);


        pressButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showWorkout(1);
            }
        });
        deadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showWorkout(2);
            }
        });
        benchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showWorkout(3);
            }
        });
        squatButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showWorkout(4);
            }
        });
        optionsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // show options method
            }
        });

    }

    private void showWorkout(int id){

        Intent intent = new Intent(MainActivity.this, Workout.class);
        Bundle b = new Bundle();
        b.putInt("key", id);
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }





}
