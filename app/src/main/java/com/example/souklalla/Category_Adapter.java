package com.example.souklalla;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.ULocale;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Category_Adapter  extends RecyclerView.Adapter<Category_Adapter.Category_holder>{

     List<product_helperclass> category;
    Context context;

    public Category_Adapter(List<product_helperclass> category, Context context) {
        this.category = category;
        this.context = context;
    }


    @NonNull
    @Override
    public Category_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_show_custom,parent,false);

        return new Category_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_holder holder, int position) {
        holder.itemView.setTag(position);
        holder.product_name.setText(category.get(position).getProd_name());
        holder.product_price.setText(category.get(position).getProd_price());
        holder.prod_wname.setText(category.get(position).getProd_wname());
        String imageBase64 = category.get(position).getProd_img();
        if (imageBase64 != null) {
            byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.product_img.setImageBitmap(decodedBitmap);
        }

        else {
            holder.product_img.setImageResource(R.drawable.photoo);
        }
        String prod_des = category.get(position).getProd_desc();
        String prod_name = category.get(position).getProd_name();
        String prod_price = category.get(position).getProd_price();
        String prod_img = category.get(position).getProd_img();

        holder.card.setOnClickListener(v -> {
            Intent intent=new Intent(context,PRODUCTBUY.class);
           intent.putExtra("prod_desc", prod_des);
           intent.putExtra("prod_name", prod_name);
           intent.putExtra("prod_price", prod_price);
           intent.putExtra("prod_img", prod_img);
            context.startActivity(intent);
        });

        }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class Category_holder extends RecyclerView.ViewHolder{
        TextView product_name , product_price , prod_wname;
        ImageView product_img;
        CardView card ;

        public Category_holder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.tv_product_name);
            product_price = itemView.findViewById(R.id.tv_price);
            prod_wname = itemView.findViewById(R.id.tv_women_name);
            product_img = itemView.findViewById(R.id.imageView6);
            card = itemView.findViewById(R.id.cd_show);
        }
    }
}
