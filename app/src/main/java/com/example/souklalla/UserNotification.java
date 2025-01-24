package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_notification);
        CardView back= findViewById(R.id.cd_back);
         String userEmail = getIntent().getStringExtra("user_email");
        String userId = getIntent().getStringExtra("userId");
        back.setOnClickListener(v -> {
            Intent intent = new Intent(UserNotification.this, USERCATEGORY.class);
            intent.putExtra("userId", userId);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });
    }
}