package com.example.derekdesktop.assign3_2017_derekaherne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * The functionality of the main activity. Two text view which are clickble with explicit intents
 */

public class MainActivity extends AppCompatActivity {
    /** Citation: Class contains code adapted from
     * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
     * Permission: MIT Licence Retrieved on:1Oth November 2017  */

    public static final String TAG = MainActivity.class.getSimpleName(); //Log tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* code adapted from post described at
         *  https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         * Permission: MIT Licence Retrieved on:1Oth November 2017  */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // give me a back button
        Log.i(TAG,"inside on create method");
    }

    /**
     * The product text view is clicked and calls the home page activity
     * @param v
     */
    public void onClickProduct (View v){

        Intent homeIntent = new Intent(this, HomePage.class);
        startActivity(homeIntent);
        Log.i(TAG," inside onClick - send button pressed");
    }

    /**
     * The prescription text view is clicked and calls the prescription activity
     * @param v
     */
    public void onClickPrescription (View v){

        Intent homeIntent = new Intent(this, Prescription.class);
        startActivity(homeIntent);
        Log.i(TAG," inside onClick - send button pressed");
    }

}