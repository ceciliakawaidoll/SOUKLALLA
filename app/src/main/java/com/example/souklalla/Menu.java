package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu);

        ImageView im1 = findViewById(R.id.food);
        ImageView im2 = findViewById(R.id.candy);
        ImageView im3 = findViewById(R.id.planner);
        ImageView im4 = findViewById(R.id.sewing);
        ImageView im5 = findViewById(R.id.knitting);
        ImageView im6 = findViewById(R.id.pottery);
        ImageView im7 = findViewById(R.id.partty);


        im1.setOnClickListener(v -> {
                Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im2.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im3.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im4.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im5.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im6.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


        im7.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            startActivity(intent);
        });


    }
}