

package com.example.derekdesktop.assign3_2017_derekaherne;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
/**
 * Created by Derek desktop on 25/12/2017.
 */

public class MainActivity extends AppCompatActivity {
    /** Citation: Class contains code adapted from
     * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
     * Permission: MIT Licence Retrieved on:1Oth November 2017  */

    public static final String TAG = MainActivity.class.getSimpleName(); //Log tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* code adapted from post described at
            https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // give me a back button
        Log.i(TAG,"inside on create method");
    }

    public void onClickProduct (View v){

        Intent homeIntent = new Intent(this, HomePage.class);
        startActivity(homeIntent);
        Log.i(TAG," inside onClick - send button pressed");
    }

    public void onClickPrescription (View v){

        Intent homeIntent = new Intent(this, Prescription.class);
        startActivity(homeIntent);
        Log.i(TAG," inside onClick - send button pressed");
    }

}