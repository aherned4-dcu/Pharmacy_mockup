package com.example.derekdesktop.assign3_2017_derekaherne;


/**
 * {@link ProductLine} represents a single Android platform release.
 * Each object has 3 properties: name, price, and image resource ID.
 */
public class ProductLine {

    // Product Name
    private String mName;
    // Product Price
    private String price;

    // Drawable resource ID
    private int mImageResourceId;

    /*
    * Create a new ProductList object.
    *
    * @param pName is the name of the product line
    * @param image is drawable reference ID that corresponds to the product line
    *
    * */
    public ProductLine(String pName, String pPrice, int imageResourceId)
    {
        mName = pName;
        price = pPrice;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the product
     */
    public String getVersionName() {
        return mName;
    }
    /**
     * Get the price of the product
     */
    public String getPrice() {
        return price;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


}
