package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class USERLOGOUTASK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_logoutask);
        Button btyes = findViewById(R.id.bt_yes);
        Button out = findViewById(R.id.bt_logout);
        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERLOGOUTASK.this, USERHOMEPROFILE.class);
            startActivity(intent);
        });

       btyes.setOnClickListener(v -> {
            Intent intent = new Intent(USERLOGOUTASK.this, USERCATEGORY.class);
            startActivity(intent);
        });


        out.setOnClickListener(v -> {
            Intent intent = new Intent(USERLOGOUTASK.this, USERHOMEPROFILE.class);
            startActivity(intent);
        });

    }
}