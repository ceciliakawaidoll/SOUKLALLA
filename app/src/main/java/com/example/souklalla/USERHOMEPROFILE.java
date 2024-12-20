package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class USERHOMEPROFILE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_homeprofile);
        ImageView imageView =findViewById(R.id.iv_logout);
        ImageView passmanager =findViewById(R.id.iv_passmanage);
        ImageView help =findViewById(R.id.iv_help);
        ImageView profile =findViewById(R.id.iv_profiledit);
        FloatingActionButton flot= findViewById(R.id.flot12);
        FloatingActionButton flotp= findViewById(R.id.ft_edit_profile);
        ImageView menu=findViewById(R.id.iv_menu12);
        ImageView like=findViewById(R.id.iv_like12);
        ImageView setting=findViewById(R.id.iv_setting12);
        ImageView home=findViewById(R.id.iv_home12);
        CardView BACK = findViewById(R.id.cd_back);

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERCATEGORY.class);
            startActivity(intent);
        });

        setting.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, Settings.class);
            startActivity(intent);
        });
        like.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, Favorite.class);
            startActivity(intent);
        });

        menu.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, Menu.class);
            startActivity(intent);
        });

       profile.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERPROFILE.class);
            startActivity(intent);
        });

        flot.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERASKPRODUCT.class);
            startActivity(intent);
        });


        flotp.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, UserEditProfile.class);
            startActivity(intent);
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERCATEGORY.class);
            startActivity(intent);
        });



        passmanager.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERMANAGEPASS.class);
            startActivity(intent);
        });

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(USERHOMEPROFILE.this, USERLOGOUTASK.class);
            startActivity(intent);
        });

    }



}