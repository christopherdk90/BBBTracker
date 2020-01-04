package com.example.bbbtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    TextView bigRepText;
    CheckBox bigRep1;
    CheckBox bigRep2;
    CheckBox bigRep3;

    String bigRepLabel;
    String bigRep1Text;
    String bigRep2Text;
    String bigRep3Text;

    String mainAccRep1Text;
    String mainAccRep2Text;
    String mainAccRep3Text;
    String mainAccRep4Text;
    String mainAccRep5Text;

    int week;
    int mainLift;
    int accLift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Return to menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initTextViews();

        // Get from shared pref
        week = 1;
        //mainLift = 125;
        //accLift = 150;

        Bundle b = getIntent().getExtras();
        int val = -1;
        if (b!= null){
            val = b.getInt("key");
        }

        switch (val) {
            case 1:
                actionBar.setTitle("Monday : Press");
                showPress();
                break;
            case 2:
                actionBar.setTitle("Tuesday : Squat");
                break;
            case 3:
                actionBar.setTitle("Thursday : Bench");
                break;
            case 4:
                actionBar.setTitle("Friday : Deadlift");
                break;
        }

        setBigRepText();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void initTextViews(){

        bigRepText = findViewById(R.id.bigRepTextView);
        bigRep1 = findViewById(R.id.bigRep1);
        bigRep2 = findViewById(R.id.bigRep2);
        bigRep3 = findViewById(R.id.bigRep3);

    }

    private String getRoundedValue(int val, double percentage){

        int fVal = (int)(5*(Math.floor((double)val*percentage/5)));

        return "" + fVal;

    }

    private void showPress() {

        // Pull from shared pref
        mainLift = 125;
        // pull bench
        accLift = 225;

        bigRepLabel = "Press : Max = " + mainLift;

    }

    private void setBigRepText(){

        if (week == 1){
            bigRep1Text = getRoundedValue(mainLift, 0.65) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.75) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.85) + "x5+";
        }
        else if (week == 2){
            bigRep1Text = getRoundedValue(mainLift, 0.70) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.80) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.90) + "x5+";
        }
        else if (week == 3) {
            bigRep1Text = getRoundedValue(mainLift, 0.75) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.85) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.95) + "x5+";
        }
        else {
            bigRep1Text = getRoundedValue(mainLift, 0.40) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.50) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.60) + "x5+";
        }

        bigRepText.setText(bigRepLabel);
        bigRep1.setText(bigRep1Text);
        bigRep2.setText(bigRep2Text);
        bigRep3.setText(bigRep3Text);

    }

}
