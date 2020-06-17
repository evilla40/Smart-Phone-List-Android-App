package com.example.project2evilla40;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] itemList1;
    private String[] itemList2;
    private int[] imageList;
    private String[] linkList;
    private ArrayAdapter<String> adapter;
    private Adapter listViewAdapter;
    private ArrayList<Product> productList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //Create lists of information necessary to create a new instance of product class
        itemList1 = getResources().getStringArray(R.array.brand_model);
        itemList2 = getResources().getStringArray(R.array.screensize_price);
        imageList = new int[]{R.drawable.galaxy_note9, R.drawable.moto_e5_play, R.drawable.google_pixel_3,
                        R.drawable.iphone_8, R.drawable.samsung_galaxy_s8, R.drawable.moto_g7};
        linkList = getResources().getStringArray(R.array.uri_list);

        //Create individual products and add to product list
        for (int i = 0; i < itemList1.length; i++) {
            Product product = new Product(itemList1[i], itemList2[i], imageList[i], linkList[i]);
            productList.add(product);

        }
        //Create and set adapter
        listViewAdapter = new Adapter(this, productList);
        listView.setAdapter(listViewAdapter);

        registerForContextMenu(listView);
    }

    //For long clicks/holds
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.i("MainActivity:", "Context menu created");
        super.onCreateContextMenu(menu, v, menuInfo);
        listView = (ListView) v;
        //Get layout for context menu
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Which option was selected from context menu
        switch (item.getItemId()) {
            case R.id.option1:
                //Get information from string arrays in strings.xml
                String[] rams = getResources().getStringArray(R.array.RAM);
                String[] storageSizes = getResources().getStringArray(R.array.storage_sizes);
                String[] prices = getResources().getStringArray(R.array.price_for_storage);
                String[] pixels = getResources().getStringArray(R.array.pixel_density);

                //Get only the information of the item that was long clicked
                String ram = rams[info.position];
                String storageSize = storageSizes[info.position];
                String price = prices[info.position];
                String pixel = pixels[info.position];

                //Create new intent to switch over to activity that will display the gathered info
                Intent intent = new Intent(MainActivity.this, ViewSpecActivity.class);

                //Send over the info that will be displayed
                intent.putExtra("RAM_spec", ram);
                intent.putExtra("size_spec", storageSize);
                intent.putExtra("price_spec", price);
                intent.putExtra("pixel_spec", pixel);

                startActivity(intent);
                return true;
            case R.id.option2:
                //Create intent to start same activity as if item was short clicked
                Intent intent2 = new Intent(MainActivity.this, DisplayPicture.class);
                //Send image to display and link to follow if image is pressed
                intent2.putExtra("image", productList.get(info.position).getPic());
                intent2.putExtra("link", productList.get(info.position).getLink());
                startActivity(intent2);
                return true;
            case R.id.option3:
                //Get list of manufacturer website links
                String[] linkList = getResources().getStringArray(R.array.manufacturer_links);
                Intent aIntent = new Intent(Intent.ACTION_VIEW);
                //Parse as URI to turn string related to the item selected into an actual link
                Uri aUri = Uri.parse(linkList[info.position]);
                aIntent.setData(aUri) ;
                aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
                //Start activity to view site in browser
                startActivity(aIntent);
                return true;
            default: return super.onContextItemSelected(item);
        }
    }

}
