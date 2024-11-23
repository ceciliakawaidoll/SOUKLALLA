package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WOMENHELLO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_hello);
        Button bt=findViewById(R.id.button3);

        bt.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHELLO.this, WOMENLOGIN.class);
            startActivity(intent);
        });


    }
}