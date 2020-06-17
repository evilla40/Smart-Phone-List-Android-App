package com.example.project2evilla40;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Product> productList;
    ArrayList<Product> productArrayList;

    public Adapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        inflater = LayoutInflater.from(context);
        this.productArrayList = new ArrayList<Product>();
        this.productArrayList.addAll(productList);
    }

    //View holder will hold individual products
    public class ViewHolder {
        TextView brand_model, screensize_price;
        ImageView image;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            //Create new holder
            holder = new ViewHolder();
            //Set layout from list_item.xml
            view = inflater.inflate(R.layout.list_item, null);
            view.setLongClickable(true);

            holder.brand_model = view.findViewById(R.id.brand_model);
            holder.screensize_price = view.findViewById(R.id.screensize_price);
            holder.image = view.findViewById(R.id.image);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        //Set text strings and images to view
        holder.brand_model.setText(productList.get(position).getBrand_model());
        holder.screensize_price.setText(productList.get(position).getScreensize_price());
        holder.image.setImageResource(productList.get(position).getPic());

        //Set click listener to open up to view full image in a separate activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter:",	"User has short clicked");
                Intent intent = new Intent(context, DisplayPicture.class);
                //Send data to view image and link if image is clicked
                intent.putExtra("image", productList.get(position).getPic());
                intent.putExtra("link", productList.get(position).getLink());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
