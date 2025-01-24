package com.example.souklalla;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class categoryAdd_Adapter extends RecyclerView.Adapter<categoryAdd_Adapter.categoryAdd_holder> {

    List<product_helperclass> categoryadd;
    Context context;
    String womenId;


    public categoryAdd_Adapter(List<product_helperclass> categoryadd, Context context, String womenId) {
        this.categoryadd = categoryadd;
        this.context = context;
        this.womenId = womenId;
    }

    @NonNull
    @Override
    public categoryAdd_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_add_style,parent,false);

        return new categoryAdd_Adapter.categoryAdd_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdd_holder holder, int position) {


        holder.itemView.setTag(position);
        holder.product_name.setText(categoryadd.get(position).getProd_name());
        holder.prod_price.setText(categoryadd.get(position).getProd_price());
        holder.prod_type.setText(categoryadd.get(position).getProd_type());
        String base64Image = categoryadd.get(position).getProd_img();
        if (base64Image != null) {
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.product_img.setImageBitmap(decodedBitmap);
        }

        else {
            holder.product_img.setImageResource(R.drawable.photoo);
        }

        String prod_id=categoryadd.get(position).getProdId();
        holder.tv_mod.setOnClickListener(v -> {
            Intent intent=new Intent(context,Produce_edit.class);
            intent.putExtra("prod_id",prod_id);
            intent.putExtra("womenId",womenId);


            context.startActivity(intent);
        });


        holder.tv_del.setOnClickListener(v -> {
            deleteProduct(prod_id);

        });

    }

    @Override
    public int getItemCount() {
        return categoryadd.size();
    }

    public class categoryAdd_holder extends RecyclerView.ViewHolder {
        TextView product_name, prod_price, prod_type, tv_del, tv_mod;
        ImageView product_img;

        public categoryAdd_holder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.tv_product_name);
            prod_price = itemView.findViewById(R.id.tv_price);
            prod_type = itemView.findViewById(R.id.tv_product_type);
            product_img = itemView.findViewById(R.id.imageView6);
            tv_del = itemView.findViewById(R.id.tv_del);
            tv_mod = itemView.findViewById(R.id.tv_mod);
        }
    }

        private void deleteProduct(String productId) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
            databaseReference.child(productId).removeValue()
                   .addOnSuccessListener(aVoid -> Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_SHORT))
                    .addOnFailureListener(e -> Toast.makeText(context, "فشل الحذف: " + e.getMessage(), Toast.LENGTH_SHORT));


    }
}
