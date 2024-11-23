package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WomenPassmanage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_passmanage);
        CardView BACK = findViewById(R.id.cd_back);
      TextView pass= findViewById(R.id.tv_fogotpass_women);

       pass.setOnClickListener(v -> {
            Intent intent = new Intent(WomenPassmanage.this, WOMENFORGOTPASS.class);
            startActivity(intent);
        });

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenPassmanage.this, WomenProfilehome.class);
            startActivity(intent);
        });

    }
}