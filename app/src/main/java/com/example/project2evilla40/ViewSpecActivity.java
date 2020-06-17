package com.example.project2evilla40;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

//This activity will display the specs of an item that was selected
public class ViewSpecActivity extends AppCompatActivity {

    TextView RAMtitle;
    TextView RAMspec;
    TextView storageTitle;
    TextView storageSpec;
    TextView priceTitle;
    TextView priceSpec;
    TextView pixelTitle;
    TextView pixelSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_specs);

        RAMtitle = (TextView) findViewById(R.id.RAM);
        RAMspec = (TextView) findViewById(R.id.RAM_spec);
        storageTitle = (TextView) findViewById(R.id.storagesizes);
        storageSpec = (TextView) findViewById(R.id.storagesizes_spec);
        priceTitle = (TextView) findViewById(R.id.priceforstorage);
        priceSpec = (TextView) findViewById(R.id.priceforstorage_spec);
        pixelTitle = (TextView) findViewById(R.id.pixeldensity);
        pixelSpec = (TextView) findViewById(R.id.pixeldensity_spec);

        //Get data sent from activity that started this one
        Intent intent = getIntent();
        String ram = intent.getStringExtra("RAM_spec");
        String storage = intent.getStringExtra("size_spec");
        String price = intent.getStringExtra("price_spec");
        String pixel = intent.getStringExtra("pixel_spec");

        //set to display received data
        RAMspec.setText(ram);
        storageSpec.setText(storage);
        priceSpec.setText(price);
        pixelSpec.setText(pixel);
    }
}
