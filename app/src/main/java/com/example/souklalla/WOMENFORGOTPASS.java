package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WOMENFORGOTPASS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_forgotpass);
        Button bt = findViewById(R.id.bt_women_email);
        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENFORGOTPASS.this, WOMENLOGIN.class);
            startActivity(intent);
        });
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENFORGOTPASS.this, WOMENVERIFICATION.class);
            startActivity(intent);
        });


    }
}