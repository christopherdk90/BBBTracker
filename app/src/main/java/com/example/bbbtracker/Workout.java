package com.example.bbbtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    TextView bigRepLabel;
    CheckBox bigRep1;
    CheckBox bigRep2;
    CheckBox bigRep3;

    String bigRepText;
    String bigRep1Text;
    String bigRep2Text;
    String bigRep3Text;

    TextView mainAccLabel;
    CheckBox mainAccRep1;
    CheckBox mainAccRep2;
    CheckBox mainAccRep3;
    CheckBox mainAccRep4;
    CheckBox mainAccRep5;

    String mainAccText;
    String mainAccRep1Text;
    String mainAccRep2Text;
    String mainAccRep3Text;
    String mainAccRep4Text;
    String mainAccRep5Text;

    TextView secAccLabel;
    CheckBox secAccRep1;
    CheckBox secAccRep2;
    CheckBox secAccRep3;
    CheckBox secAccRep4;
    CheckBox secAccRep5;

    String secAccText;
    String secAccRep1Text;
    String secAccRep2Text;
    String secAccRep3Text;
    String secAccRep4Text;
    String secAccRep5Text;

    int weekVal;
    int pressVal;
    int deadVal;
    int benchVal;
    int squatVal;

    int mainLift;
    int accLift;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Return to menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        pref = getApplicationContext().getSharedPreferences("lifts", 0);

        initTextViews();

        initSharedPrefs();

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
                actionBar.setTitle("Tuesday : Deadlift");
                showDead();
                break;
            case 3:
                actionBar.setTitle("Thursday : Bench");
                showBench();
                break;
            case 4:
                actionBar.setTitle("Friday : Squat");
                showSquat();
                break;
        }

        setBigRep();
        setAcc();
        setSecondAcc();

        setText();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void initSharedPrefs(){

        weekVal = pref.getInt("week", 1);
        pressVal = pref.getInt("press", 135);
        deadVal = pref.getInt("dead", 135);
        benchVal = pref.getInt("bench", 135);
        squatVal = pref.getInt("squat", 135);

    }

    private void initTextViews(){

        bigRepLabel = findViewById(R.id.bigRepTextView);
        bigRep1 = findViewById(R.id.bigRep1);
        bigRep2 = findViewById(R.id.bigRep2);
        bigRep3 = findViewById(R.id.bigRep3);

        mainAccLabel = findViewById(R.id.mainAccTextView);
        mainAccRep1 = findViewById(R.id.mainAccessRep1);
        mainAccRep2 = findViewById(R.id.mainAccessRep2);
        mainAccRep3 = findViewById(R.id.mainAccessRep3);
        mainAccRep4 = findViewById(R.id.mainAccessRep4);
        mainAccRep5 = findViewById(R.id.mainAccessRep5);

        secAccLabel = findViewById(R.id.secAccTextView);
        secAccRep1 = findViewById(R.id.secAccessRep1);
        secAccRep2 = findViewById(R.id.secAccessRep2);
        secAccRep3 = findViewById(R.id.secAccessRep3);
        secAccRep4 = findViewById(R.id.secAccessRep4);
        secAccRep5 = findViewById(R.id.secAccessRep5);

    }

    private String getRoundedValue(int val, double percentage){

        int fVal = (int)(5*(Math.floor((double)val*percentage/5)));

        return "" + fVal;

    }

    private void showPress() {

        // Pull from shared pref
        mainLift = pressVal;
        // pull bench
        accLift = benchVal;

        bigRepText = "Press : TM = " + mainLift;
        mainAccText = "Bench : TM = " + accLift + "\n10 reps";
        secAccText = "Lat work";

    }

    private void showDead() {

        mainLift = deadVal;
        accLift = squatVal;

        bigRepText = "Deadlift : TM = " + mainLift;
        mainAccText = "Squat : TM = " + accLift + "\n10 reps";
        secAccText = "Abs";

    }

    private void showBench() {

        mainLift = benchVal;
        accLift = pressVal;

        bigRepText = "Bench : TM = " + mainLift;
        mainAccText = "Press : TM = " + accLift + "\n10 reps";
        secAccText = "Lat work";

    }

    private void showSquat() {

        mainLift = squatVal;
        accLift = deadVal;

        bigRepText = "Squat : TM = " + mainLift;
        mainAccText = "Deadlift : TM = " + accLift + "\n10 reps";
        secAccText = "Abs";

    }

    private void setBigRep(){

        if (weekVal == 1){
            bigRep1Text = getRoundedValue(mainLift, 0.65) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.75) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.85) + "x5+";
        }
        else if (weekVal == 2){
            bigRep1Text = getRoundedValue(mainLift, 0.70) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.80) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.90) + "x5+";
        }
        else if (weekVal == 3) {
            bigRep1Text = getRoundedValue(mainLift, 0.75) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.85) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.95) + "x5+";
        }
        else {
            bigRep1Text = getRoundedValue(mainLift, 0.40) + "x5";
            bigRep2Text = getRoundedValue(mainLift, 0.50) + "x5";
            bigRep3Text = getRoundedValue(mainLift, 0.60) + "x5+";
        }

    }

    private void setAcc(){

        mainAccRep1Text = getRoundedValue(accLift, 0.50);
        mainAccRep2Text = getRoundedValue(accLift, 0.60);
        mainAccRep3Text = getRoundedValue(accLift, 0.70);
        mainAccRep4Text = getRoundedValue(accLift, 0.60);
        mainAccRep5Text = getRoundedValue(accLift, 0.50);

    }

    private void setSecondAcc(){

        secAccRep1Text = "1";
        secAccRep2Text = "2";
        secAccRep3Text = "3";
        secAccRep4Text = "4";
        secAccRep5Text = "5";

    }

    private void setText(){

        bigRepLabel.setText(bigRepText);
        bigRep1.setText(bigRep1Text);
        bigRep2.setText(bigRep2Text);
        bigRep3.setText(bigRep3Text);

        mainAccLabel.setText(mainAccText);
        mainAccRep1.setText(mainAccRep1Text);
        mainAccRep2.setText(mainAccRep2Text);
        mainAccRep3.setText(mainAccRep3Text);
        mainAccRep4.setText(mainAccRep4Text);
        mainAccRep5.setText(mainAccRep5Text);

        secAccLabel.setText(secAccText);
        secAccRep1.setText(secAccRep1Text);
        secAccRep2.setText(secAccRep2Text);
        secAccRep3.setText(secAccRep3Text);
        secAccRep4.setText(secAccRep4Text);
        secAccRep5.setText(secAccRep5Text);


    }

}
