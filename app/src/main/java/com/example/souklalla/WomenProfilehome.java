package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WomenProfilehome extends AppCompatActivity {
    String womenEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_profilehome);

        // Retrieve email passed from previous activity
        womenEmail = getIntent().getStringExtra("women_email");
        String womenId = getIntent().getStringExtra("womenId");

        ImageView imageView = findViewById(R.id.WOMEN_logout);
        ImageView passmanager = findViewById(R.id.WOMEN_passmanage);
        ImageView help = findViewById(R.id.WOMEN_help);
        ImageView profile = findViewById(R.id.WOMEN_profiledit);
        FloatingActionButton flot = findViewById(R.id.flotADD);
        FloatingActionButton edfloat = findViewById(R.id.ft_edit_profile_women);
        CardView BACK = findViewById(R.id.CD_back);

        // Add listeners
        flot.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, PRODUCTADD.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        edfloat.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenEditProfile.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        passmanager.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenPassmanage.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WOMENHOMEPAGE.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
            finish();
        });

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenLogoutask.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfilehome.this, WomenProfile.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });
    }
}
