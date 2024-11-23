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

public class UserEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_edit_profile);
        CardView BACK = findViewById(R.id.cd_back);
        Button button = findViewById(R.id.button7);


        button.setOnClickListener(v -> {
            Intent intent = new Intent(UserEditProfile.this, USERHOMEPROFILE.class);
            startActivity(intent);
        });
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(UserEditProfile.this, USERHOMEPROFILE.class);
            startActivity(intent);
        });
    }
}