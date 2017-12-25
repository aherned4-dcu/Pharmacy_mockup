package com.example.derekdesktop.assign3_2017_derekaherne;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * {@link HomePage} shows a list of products and prices
 * For each product, display the name and price
 */
public class HomePage extends AppCompatActivity {

    public static final String TAG = HomePage.class.getSimpleName(); //Log tag
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of ProductLine objects
        final ArrayList<ProductLine> productLines = new ArrayList<ProductLine>();
        productLines.add(new ProductLine(getString(R.string.gift), getString(R.string.gift_P), R.drawable.gift));
        productLines.add(new ProductLine(getString(R.string.pills), getString(R.string.pills_P), R.drawable.pills));
        productLines.add(new ProductLine(getString(R.string.perfume), getString(R.string.perfume_P), R.drawable.perfume));
        productLines.add(new ProductLine(getString(R.string.cream), getString(R.string.cream_P), R.drawable.cream));
        productLines.add(new ProductLine(getString(R.string.lip), getString(R.string.lip_P), R.drawable.lipstick));
        productLines.add(new ProductLine(getString(R.string.bath), getString(R.string.bath_P), R.drawable.bath));
        productLines.add(new ProductLine(getString(R.string.camera), getString(R.string.camera_P), R.drawable.camera));


        // Create an {@link ProductLineAdaptorAdapter}, whose data source is a list of
        // {@link ProductLine}s. The adapter knows how to create list item views for each item
        // in the list.
        final ProductLineAdaptor productAdapter = new ProductLineAdaptor(this, productLines);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.listview_product);
        listView.setAdapter(productAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /** Citation: Class contains code adapted from
             * URL: https://stackoverflow.com/questions/18314618/listview-in-android-with-toast-message
             * Permission: MIT Licence Retrieved on:18th December 2017  */
            public void onItemClick(AdapterView<?> arg0, View v, int arg2,long arg3) {
                TextView tv = (TextView) v.findViewById(R.id.version_name);
                Toast.makeText(getApplicationContext(), tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}