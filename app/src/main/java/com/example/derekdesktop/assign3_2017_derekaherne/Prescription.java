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

    //https://developer.android.com/guide/topics/ui/controls/spinner.html

    public static final String TAG = Prescription.class.getSimpleName(); //Log tag



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



    public void onClickSend (View v){
        //extract text from EditText fields
        /* code adapted from post described at
           https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
        */
        //Capture the text from the previous activity
        TextView textViewName = (TextView) findViewById(R.id.name);
        TextView textViewInfo = (TextView) findViewById(R.id.otherInfo);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_time);
        String selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        String to = getString(R.string.email_recip);
        String subject1 = getString(R.string.name)+": "+textViewName.getText().toString()+"\n"+getString(R.string.add_inst)+": "+textViewInfo.getText().toString()+"\n"+getString(R.string.coll_t)+": "+selected;
        //open an email app and set the fields using the text from the previous activity
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822"); // ensure only email apps
        i.setData(Uri.parse("mailto:"+to)); //recipient
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject)+textViewName.getText().toString()); //subject
        i.putExtra(android.content.Intent.EXTRA_TEXT,subject1); //bofy
        startActivity(Intent.createChooser(i, "Send mail..."));
        Log.i(TAG," inside onClickSend");
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