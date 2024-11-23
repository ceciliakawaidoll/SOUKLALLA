package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class USERCATEGORYSHOW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_categoryshow);
        CardView BACK = findViewById(R.id.cd_back);
        RecyclerView category_list = findViewById(R.id.rv_product);


        List<Category> category = new ArrayList<>();
        for (int i=0;i<10;i++){
            category.add(new Category("إسم المنتوج  "+ i) );
        }


       Category_Adapter adapter = new Category_Adapter(category,this);
      category_list.setAdapter(adapter);
        category_list.setLayoutManager(new LinearLayoutManager(this));

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORYSHOW.this, USERCATEGORY.class);
            startActivity(intent);
        });

    }
}