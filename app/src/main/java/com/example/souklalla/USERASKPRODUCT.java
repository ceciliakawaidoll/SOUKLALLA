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

public class USERASKPRODUCT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_askpoduct);

        Button ask= findViewById(R.id.bt_asking);
        CardView BACK = findViewById(R.id.cd_back);
        String userEmail = getIntent().getStringExtra("user_email");
        String userId = getIntent().getStringExtra("userId");
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERASKPRODUCT.this, USERCATEGORY.class);
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });

        ask.setOnClickListener(v -> {
            Intent intent = new Intent(USERASKPRODUCT.this, USERCATEGORY.class);
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });


    }

}