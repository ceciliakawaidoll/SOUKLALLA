package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UserResetpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_resetpass);
        EditText newpass=findViewById(R.id.user_newpass);
        EditText newconfipass=findViewById(R.id.user_newconfpass);
        Button reset=findViewById(R.id.user_resetpass);

        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(UserResetpass.this, UserVerification.class);
            startActivity(intent);
        });

       reset.setOnClickListener(v -> {
            Intent intent = new Intent(UserResetpass.this, SuccessfullPass.class);
            startActivity(intent);
        });

    }
}