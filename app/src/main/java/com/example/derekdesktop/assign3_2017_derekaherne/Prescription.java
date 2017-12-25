package com.example.derekdesktop.assign3_2017_derekaherne;

import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Spinner;


/**
 * Created by Derek desktop on 25/12/2017.
 */

public class Prescription extends AppCompatActivity {
    /** Citation: Class contains code adapted from
     * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
     * Permission: MIT Licence Retrieved on:1Oth November 2017  */

    public static final String TAG = Prescription.class.getSimpleName(); //Log tag
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* code adapted from post described at
            https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // give me a back button
        Log.i(TAG,"inside on create method");
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void onClickSend (View v){
        //extract text from EditText fields
        /* code adapted from post described at
           https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
        */
        //Capture the text from the previous activity
        TextView textViewName = (TextView) findViewById(R.id.name);
        TextView textViewInfo = (TextView) findViewById(R.id.otherInfo);

        String to = "pharmacy@pharmacy.com";
        String subject1 = "Name: "+textViewName.getText().toString()+"\nAdditional Instructions: "+textViewInfo.getText().toString()+"\nCollection Time: "+textViewInfo.getText().toString();

        //open an email app and set the fields using the text from the previous activity
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822"); // ensure only email apps
        i.setData(Uri.parse("mailto:"+to)); //recipient
        i.putExtra(Intent.EXTRA_SUBJECT, "Prescription Order"); //subject
        i.putExtra(android.content.Intent.EXTRA_TEXT,subject1); //bofy
        startActivity(Intent.createChooser(i, "Send mail..."));
        Log.i(TAG," inside onSend");
    }

    public void onClickCam (View v){
        /* open camera in still mode/* code adapted from tutorial described at
        * URL: //https://developer.android.com/guide/components/intents-common.html
        */
        // open camera in still mode
        Intent camIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivity(camIntent);
        Log.i(TAG," inside onClickCam");
    }

}