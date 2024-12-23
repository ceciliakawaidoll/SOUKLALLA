package com.example.souklalla;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class categoryAdapter extends BaseAdapter {
    Context context;
    String[] categoryname;
    int[] image;
    LayoutInflater inflater;

    public categoryAdapter(Context context, String[] categoryname, int[] image) {
        this.context = context;
        this.categoryname = categoryname;
        this.image = image;
    }

    public categoryAdapter() {

    }

    @Override
    public int getCount() {
        return categoryname.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null)
            inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.categories_style,null);
        }
        ImageView imageView=convertView.findViewById(R.id.gridimage);
        TextView textView=convertView.findViewById(R.id.gridtext);
        CardView card = convertView.findViewById(R.id.cd_category);
        textView.setText(categoryname[position]);
        imageView.setImageResource(image[position]);
        card.setOnClickListener(v -> {
            Intent intent=new Intent(context,USERCATEGORYSHOW.class);

            intent.putExtra("type", categoryname[position]);
            context.startActivity(intent);
        });

        return convertView;
    }
}
