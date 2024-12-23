package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WOMENHOMEPAGE extends AppCompatActivity {
    String womenEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_homepage);
        FloatingActionButton ft = findViewById(R.id.flotadd);
        ImageView noti= findViewById(R.id.iv_notifi);

        womenEmail = getIntent().getStringExtra("women_email");
        ImageView profile= findViewById(R.id.iv_profile);

        ft.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, PRODUCTADD.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenProfilehome.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        noti.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenNotification.class);
            startActivity(intent);
        });

    }
}