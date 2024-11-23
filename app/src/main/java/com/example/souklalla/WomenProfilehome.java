package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WomenProfilehome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_profilehome);
        ImageView imageView =findViewById(R.id.WOMEN_logout);
        ImageView passmanager =findViewById(R.id.WOMEN_passmanage);
        ImageView help =findViewById(R.id.WOMEN_help);
        ImageView profile =findViewById(R.id.WOMEN_profiledit);
        FloatingActionButton flot= findViewById(R.id.flotADD);
        FloatingActionButton edfloat = findViewById(R.id.ft_edit_profile_women);
        CardView BACK = findViewById(R.id.CD_back);


        flot.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, PRODUCTADD.class);
            startActivity(intent);
        });

       edfloat.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this,WomenEditProfile.class);
            startActivity(intent);
        });


        passmanager.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenPassmanage.class);
            startActivity(intent);
        });


        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WOMENHOMEPAGE.class);
            startActivity(intent);
        });

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenLogoutask.class);
            startActivity(intent);
        });

       profile.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenProfile.class);
            startActivity(intent);
        });


    }

}