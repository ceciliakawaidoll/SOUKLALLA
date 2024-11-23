package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WomenNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_notification);
        CardView back= findViewById(R.id.cd_back);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(WomenNotification.this, WOMENHOMEPAGE.class);
            startActivity(intent);
        });
    }
}