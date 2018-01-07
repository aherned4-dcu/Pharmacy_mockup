package com.example.derekdesktop.assign3_2017_derekaherne;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Generates the functionality for the Perscription activity.
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
    public String photoFileName = "photo.jpg";

    private ImageView mPhotoCapturedImageView;
    private String mImageFileLocation = "";

    @Override
    /**
     * Creation of the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        /* code adapted from post described at
            https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // give me a back button


        Log.i(TAG, "inside on create method");

    }

    /**
     * On click of the send button the edit test values and photo are used by an email intent
     * @param v
     */

    public void onClickSend(View v) {
        //extract text from EditText fields
        /* /** Citation: Class contains code adapted from
           https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
         * Permission: MIT Licence Retrieved on:25th Ddecember 2017  */

        //Capture the text
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
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + textViewName.getText().toString()); //subject
        i.putExtra(android.content.Intent.EXTRA_TEXT, subject1); //body
        if (URI != null) {
            i.putExtra(Intent.EXTRA_STREAM, URI);
        }
        startActivity(Intent.createChooser(i, "Send mail..."));
        Log.i(TAG, " inside onClickSend");
    }

    /**
     * On image view click open the camera app and take a photo. The URI is generated with temporary permissions
     * @param v
     *
     */
    public void onClickCam(View v) {
        /** Citation: Class contains code adapted from
         * URL: //https://developer.android.com/guide/components/intents-common.html
         * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

        /** Citation: Class contains code adapted from
         *  https://www.nigeapptuts.com/camera-intent-android-nougat-support/
         * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

        // open camera in still mode
        Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        // Create a File reference to access to future access
        File photoFile;
        photoFile = getPhotoFileUri(photoFileName);

        String authorities=getApplicationContext().getPackageName()+".fileprovider";
        Uri fileProvider = FileProvider.getUriForFile(this, authorities, photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
        URI=Uri.fromFile(photoFile);
        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(takePictureIntent, PICK_FROM_GALLERY);
        }
    }

    @Override
    /**
     * The activity result which passes the taken photo's bitmap to the image view
     * The photo is made available for the email app.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_FROM_GALLERY) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Picture taken successfully", Toast.LENGTH_SHORT).show();
               // Bundle extras = data.getExtras();
               // Bitmap photoCapturedBitmap = (Bitmap) extras.get("data");
               // mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
               // photoCapturedBitmap = BitmapFactory.decodeFile(mImageFileLocation);
               // mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);

            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }



    /**
     * Returns the File for a photo stored on disk given the fileName
     * @param fileName
     * @return
     */

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

    /**
     * Returns true if external storage for photos is available
     * @return
     */
    private boolean isExternalStorageAvailable() {
        /** Citation: Class contains code adapted from
         * URL: https://stackoverflow.com/questions/14545139/android-back-button-in-the-title-bar
         * Permission: MIT Licence Retrieved on:1Oth November 2017  */

        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Create a unique image file name
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        /** Citation: Class contains code adapted from
         *  https://www.nigeapptuts.com/camera-intent-android-nougat-support/
         * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName,".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath();

        return image;

    }

    /**
     *
     */

    void setReducedImageSize() {
        /** Citation: Class contains code adapted from
         *  https://www.nigeapptuts.com/camera-intent-android-nougat-support/
         * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

        int targetImageViewWidth = mPhotoCapturedImageView.getWidth();
        int targetImageViewHeight = mPhotoCapturedImageView.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        int cameraImageWidth = bmOptions.outWidth;
        int cameraImageHeight = bmOptions.outHeight;

        int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth, cameraImageHeight/targetImageViewHeight);
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inJustDecodeBounds = false;

        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        mPhotoCapturedImageView.setImageBitmap(photoReducedSizeBitmp);


    }










}