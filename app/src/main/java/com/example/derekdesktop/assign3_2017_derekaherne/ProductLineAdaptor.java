package com.example.derekdesktop.assign3_2017_derekaherne;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
* {@link ProductLineAdaptor} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link ProductLine} objects.
* */
public class ProductLineAdaptor extends ArrayAdapter<ProductLine> {
    /** Citation: Class contains code adapted from
     * URL: https://github.com/udacity/ud839_CustomAdapter_Example
     * Permission: MIT Licence Retrieved on:1Oth November 2017  */

    public static final String LOG_TAG = ProductLineAdaptor.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param productLine A List of ProductLine objects to display in a list
     */
    public ProductLineAdaptor(Activity context, ArrayList<ProductLine> productLine) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, productLine);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        String item = String.valueOf(getItem(position));
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link ProductLine} object located at this position in the list
        ProductLine currentProductLine = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        // Get the version name from the current ProductLine object and
        // set this text on the name TextView
        nameTextView.setText(currentProductLine.getVersionName());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.version_number);
        // Get the version name from the current ProductLine object and
        // set this text on the price TextView
        priceTextView.setText(currentProductLine.getPrice());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current ProductLine object and
        // set the image to iconView
        iconView.setImageResource(currentProductLine.getImageResourceId());



        // Return the whole list item layout (containing TextViews and an ImageView)
        // so that it can be shown in the ListView

        return listItemView;
    }

}
