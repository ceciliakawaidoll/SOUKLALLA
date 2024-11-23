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

     List<Category> category;
    Context context;

    public Category_Adapter(List<Category> category, Context context) {
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
        holder.product_name.setText(category.get(position).getProduct_name());
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
        TextView product_name ;
        CardView card ;

        public Category_holder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.tv_product_name);
            card = itemView.findViewById(R.id.cd_show);
        }
    }
}
