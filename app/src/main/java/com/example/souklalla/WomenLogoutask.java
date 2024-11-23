package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WomenLogoutask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_logoutask);
        CardView BACK = findViewById(R.id.cd_back);
        Button btyes = findViewById(R.id.bt_yes);
        Button out = findViewById(R.id.bt_logout);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenLogoutask.this, WomenProfilehome.class);
            startActivity(intent);
        });



        btyes.setOnClickListener(v -> {
            Intent intent = new Intent(WomenLogoutask.this, WOMENHELLO.class);
            startActivity(intent);
        });


        out.setOnClickListener(v -> {
            Intent intent = new Intent(WomenLogoutask.this, WomenProfilehome.class);
            startActivity(intent);
        });
    }
}