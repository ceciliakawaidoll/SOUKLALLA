package com.example.souklalla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentVerifi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.payment_verifi);
        Button bt =findViewById(R.id.bt_passed);
         CardView BACK = findViewById(R.id.cd_back);


        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentVerifi.this, Payment.class);
            startActivity(intent);
        });
       bt.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentVerifi.this, PaymentSuccessfull.class);
            startActivity(intent);
        });
    }
}