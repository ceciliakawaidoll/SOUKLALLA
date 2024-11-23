package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WOMENVERIFICATION extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_verification);
        Button bt = findViewById(R.id.butpass);
        CardView BACK = findViewById(R.id.cd_back);

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENVERIFICATION.this, WOMENFORGOTPASS.class);
            startActivity(intent);
        });
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENVERIFICATION.this, WOMENRESETPASS.class);
            startActivity(intent);
        });

    }
}