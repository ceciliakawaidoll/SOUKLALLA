package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class USERMANAGEPASS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_managepass);
        Button pass=findViewById(R.id.buton_pass);
        CardView BACK = findViewById(R.id.cd_back);
        TextView textView=findViewById(R.id.tv_forgotpassuser);

          String userEmail = getIntent().getStringExtra("user_email");
       textView.setOnClickListener(v -> {
            Intent intent = new Intent(USERMANAGEPASS.this, UserForgotpass.class);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERMANAGEPASS.this, USERHOMEPROFILE.class);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });

        pass.setOnClickListener(v -> {
            Intent intent = new Intent(USERMANAGEPASS.this, USERHOMEPROFILE.class);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });
    }
}