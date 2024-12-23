package com.example.souklalla;

import android.content.Context;
import android.content.Intent;
import android.icu.util.ULocale;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.product_img.setText(category.get(position).getProd_img());


        holder.card.setOnClickListener(v -> {
            Intent intent=new Intent(context,PRODUCTBUY.class);
            context.startActivity(intent);
        });

        }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class Category_holder extends RecyclerView.ViewHolder{
        TextView product_name , product_price , prod_wname, product_img;
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
