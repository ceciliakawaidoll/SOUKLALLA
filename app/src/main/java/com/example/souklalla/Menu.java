package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView type1=findViewById(R.id.tv_food);
        TextView type2=findViewById(R.id.tv_candy);
        TextView type3=findViewById(R.id.tv_planner);
        TextView type4=findViewById(R.id.tv_sewing);
        TextView type5=findViewById(R.id.tv_knitting);
        TextView type6=findViewById(R.id.tv_pottery);
        TextView type7=findViewById(R.id.tv_party);

        String userEmail = getIntent().getStringExtra("user_email");
        String userId = getIntent().getStringExtra("userId");

        im1.setOnClickListener(v -> {
                Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
                intent.putExtra("type1",type1.getText().toString());
                intent.putExtra("userId", userId);
                intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im2.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type2",type2.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im3.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type3",type3.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im4.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type4",type4.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im5.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type5",type5.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im6.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type6",type6.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


        im7.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, USERCATEGORYSHOW.class);
            intent.putExtra("type7",type7.getText().toString());
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


    }
}