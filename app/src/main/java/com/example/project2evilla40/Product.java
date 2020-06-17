package com.example.project2evilla40;

import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;

public class Product {
    private String brand_model;
    private String screensize_price;
    private int pic;
    private String link;

    public Product(String brandModel, String screenSizePrice, int image, String uri) {
        brand_model = brandModel;
        screensize_price = screenSizePrice;
        pic = image;
        link = uri;

    }

    //Getter functions

    public String getBrand_model() {
        return brand_model;
    }

    public String getScreensize_price() {
        return screensize_price;
    }

    public int getPic() {
        return this.pic;
    }

    public String getLink() { return this.link; }

}
