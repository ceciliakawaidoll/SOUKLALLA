package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WOMENLOGIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_login);
        TextView text = findViewById(R.id.tv_acont);
        TextView textpass = findViewById(R.id.tv_pass);

        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENHELLO.class);
            startActivity(intent);
        });
       text.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENCREATACOUNT.class);
            startActivity(intent);
        });

        textpass.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENFORGOTPASS.class);
            startActivity(intent);
        });

    }
}