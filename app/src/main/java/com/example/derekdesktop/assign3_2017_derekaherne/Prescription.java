package com.example.derekdesktop.assign3_2017_derekaherne;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Spinner;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Derek desktop on 25/12/2017.
 */

public class Prescription extends AppCompatActivity {
    /** Citation: Class contains code adapted from
     * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
     * Permission: MIT Licence Retrieved on:1Oth November 2017  */

    /**
     * Citation: Class contains code adapted from
     * URL: https://developer.android.com/guide/topics/ui/controls/spinner.html
     * Permission: MIT Licence Retrieved on:1Oth November 2017
     */

    public static final String TAG = Prescription.class.getSimpleName(); //Log tag
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    String mCurrentPhotoPath;
    String working = "not working";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* code adapted from post described at
            https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // give me a back button


        Log.i(TAG, "inside on create method");

    }

    public void onClickSend(View v) {
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
        String subject1 = getString(R.string.name) + ": " + textViewName.getText().toString() + "\n" + getString(R.string.add_inst) + ": " + textViewInfo.getText().toString() + "\n" + getString(R.string.coll_t) + ": " + selected;
        //open an email app and set the fields using the text from the previous activity
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822"); // ensure only email apps
        i.setData(Uri.parse("mailto:" + to)); //recipient
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + working + textViewName.getText().toString()); //subject
        i.putExtra(android.content.Intent.EXTRA_TEXT, mCurrentPhotoPath); //body
        if (URI != null) {
            i.putExtra(Intent.EXTRA_STREAM, URI);
        }
        startActivity(Intent.createChooser(i, "Send mail..."));
        Log.i(TAG, " inside onClickSend");
    }

    public void onClickCam(View v) {
        /* code adapted from tutorial described at
        * URL: //https://developer.android.com/guide/components/intents-common.html
        */
        // open camera in still mode

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, PICK_FROM_GALLERY);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            File f = new File(mCurrentPhotoPath);
            URI = Uri.fromFile(f);
            working = "RESULT_OK";
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

            return file;
        }
        return null;
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        /** Citation: Class contains code adapted from
         * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         * Permission: MIT Licence Retrieved on:1Oth November 2017  */

        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }









}