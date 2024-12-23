package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WOMENHOMEPAGE extends AppCompatActivity {
    String womenEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_homepage);
        FloatingActionButton ft = findViewById(R.id.flotadd);
        ImageView noti= findViewById(R.id.iv_notifi);




        RecyclerView category_list = findViewById(R.id.rv_product_home);


        List<CategoryAdd> category = new ArrayList<>();
        for (int i=0;i<10;i++){
            category.add(new CategoryAdd("المنتجات" +i));

        }
        categoryAdd_Adapter addAdapter = new categoryAdd_Adapter(category);
        category_list.setAdapter(addAdapter);
        category_list.setLayoutManager(new LinearLayoutManager(this));

        womenEmail = getIntent().getStringExtra("women_email");
        ImageView profile= findViewById(R.id.iv_profile);

        ft.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, PRODUCTADD.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenProfilehome.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        noti.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenNotification.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

    }
}