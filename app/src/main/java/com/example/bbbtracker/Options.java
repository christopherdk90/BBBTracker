package com.example.bbbtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Options extends AppCompatActivity {

    int pressVal;
    int deadVal;
    int benchVal;
    int squatVal;

    int week;

    int[] vals = {week, pressVal, deadVal, benchVal, squatVal};

    Button pressButton;
    Button deadButton;
    Button benchButton;
    Button squatButton;
    Button incButton;
    Button decButton;
    Button saveButton;
    Button weekOne;
    Button weekTwo;
    Button weekThree;
    Button weekFour;

    TextView weekText;
    TextView pressText;
    TextView deadText;
    TextView benchText;
    TextView squatText;

    TextView[] textViews = new TextView[5];

    int selected = 0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Return to menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        pref = getApplicationContext().getSharedPreferences("lifts", 0);
        editor = pref.edit();

        loadValues();

        initButtons();
        initTextViews();




    }

    // Back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void initTextViews(){

        pressText = findViewById(R.id.pressWeight);
        deadText = findViewById(R.id.deadWeight);
        benchText = findViewById(R.id.benchWeight);
        squatText = findViewById(R.id.squatWeight);

        weekText = findViewById(R.id.weekText);

        textViews[1] = pressText;
        textViews[2] = deadText;
        textViews[3] = benchText;
        textViews[4] = squatText;

        setTexts();

    }

    private void setTexts(){

        String weekStr = "Week: " + vals[0];

        String pressStr = vals[1] + "";
        String deadStr = vals[2] + "";
        String benchStr = vals[3] + "";
        String squatStr = vals[4] + "";

        pressText.setText(pressStr);
        deadText.setText(deadStr);
        benchText.setText(benchStr);
        squatText.setText(squatStr);

        weekText.setText(weekStr);

    }

    private void initButtons(){

        // Grab buttons
        pressButton = findViewById(R.id.pressBtn);
        deadButton = findViewById(R.id.deadBtn);
        benchButton = findViewById(R.id.benchBtn);
        squatButton = findViewById(R.id.squatBtn);
        incButton = findViewById(R.id.incBtn);
        decButton = findViewById(R.id.decBtn);
        saveButton = findViewById(R.id.saveBtn);
        weekOne = findViewById(R.id.weekOneBtn);
        weekTwo = findViewById(R.id.weekTwoBtn);
        weekThree = findViewById(R.id.weekThreeBtn);
        weekFour = findViewById(R.id.weekFourBtn);

        // Set listeners
        pressButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setActive(1);
            }
        });
        deadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setActive(2);
            }
        });
        benchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setActive(3);
            }
        });
        squatButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setActive(4);
            }
        });
        decButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (selected != 0){
                    vals[selected] = vals[selected] - 5;
                    setTexts();
                }

            }
        });
        incButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (selected != 0){
                    vals[selected] = vals[selected] + 5;
                    setTexts();
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                saveValues();
            }
        });
        weekOne.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                vals[0] = 1;
                setTexts();
            }
        });
        weekTwo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                vals[0] = 2;
                setTexts();
            }
        });
        weekThree.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                vals[0] = 3;
                setTexts();
            }
        });
        weekFour.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                vals[0] = 4;
                setTexts();
            }
        });

    }

    private void setActive(int id){

        selected = id;

        TextView activeText = textViews[id];

        for (int i = 1; i < 5; i++){

            if (i == id){
                continue;
            }

            textViews[i].setTypeface(null, Typeface.NORMAL);
        }

        activeText.setTypeface(activeText.getTypeface(), Typeface.BOLD);

    }

    private void loadValues(){

        vals[0] = pref.getInt("week", 1);
        vals[1] = pref.getInt("press", 95);
        vals[2] = pref.getInt("dead", 225);
        vals[3] = pref.getInt("bench", 135);
        vals[4] = pref.getInt("squat", 135);

    }

    private void saveValues(){

        editor.putInt("week", vals[0]);
        editor.putInt("press", vals[1]);
        editor.putInt("dead", vals[2]);
        editor.putInt("bench", vals[3]);
        editor.putInt("squat", vals[4]);

        editor.commit();

    }


}
