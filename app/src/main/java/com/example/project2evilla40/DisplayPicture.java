package com.example.project2evilla40;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class DisplayPicture extends AppCompatActivity {
    private ImageView pic;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pic);
        pic = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        int temp = 0;
        //Get image and link from data set as extra
        int i = intent.getIntExtra("image", temp);
        link = intent.getStringExtra("link");
        //Set to display image
        pic.setImageResource(i);
        //Set listener for short clicks to open link
        pic.setOnClickListener(ClickImageListener);
    }

    //If picture is clicked, then open browser to specified website
    public View.OnClickListener ClickImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DisplayPicture:",	"Picture has been clicked");
            Intent aIntent = new Intent(Intent.ACTION_VIEW);
            //Parse as URI to turn string into an actual link
            Uri aUri = Uri.parse(link);
            aIntent.setData(aUri) ;
            aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
            //Start activity to view site in browser
            startActivity(aIntent);
        }
    };
}
