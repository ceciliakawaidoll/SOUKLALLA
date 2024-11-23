package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WOMENRESETPASS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_resetpass);

        Button bt=findViewById(R.id.butstart);
        CardView BACK = findViewById(R.id.cd_back);

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENRESETPASS.this, WOMENVERIFICATION.class);
            startActivity(intent);
        });

        bt.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENRESETPASS.this, SuccessfullPass.class);
            startActivity(intent);
        });

    }
}