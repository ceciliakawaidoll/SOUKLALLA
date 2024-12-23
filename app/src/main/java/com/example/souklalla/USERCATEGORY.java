package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/

public class USERCATEGORY extends AppCompatActivity {
    String userEmail;

    String[] categoryname ={"الأطباق","الحلويات","بلانر","الخياطة ","الحياكة","الفخار","المناسبات"};

    int[] image ={R.drawable.foood,R.drawable.candy,R.drawable.planner,R.drawable.sewing_2,R.drawable.knitting,R.drawable.pottery,R.drawable.party};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_category);
        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView=findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/
        GridView gridView =  findViewById(R.id.gridview);
        FloatingActionButton ft= findViewById(R.id.flot10);
        ImageView menu=findViewById(R.id.iv_menu);
        ImageView like=findViewById(R.id.iv_like);
        ImageView setting=findViewById(R.id.iv_setting);

        userEmail = getIntent().getStringExtra("user_email");


        ImageView home=findViewById(R.id.iv_home);
        ImageView profi=findViewById(R.id.iv_profile);
        ImageView notifi=findViewById(R.id.iv_notifi);

        setting.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, Settings.class);
            startActivity(intent);
        });


        home.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, USERCATEGORY.class);
            startActivity(intent);
        });

        like.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, Favorite.class);
            startActivity(intent);
        });

        menu.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, Menu.class);
            startActivity(intent);
        });


        notifi.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, UserNotification.class);
            startActivity(intent);
        });

       profi.setOnClickListener(v -> {
           if (userEmail == null) {
               Intent intent = new Intent(USERCATEGORY.this, UserLogin.class);
               startActivity(intent);
           }else {
           Intent intent = new Intent(USERCATEGORY.this, USERHOMEPROFILE.class);
           intent.putExtra("user_email", userEmail);
           startActivity(intent);}
        });

       ft.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORY.this, USERASKPRODUCT.class);
            startActivity(intent);
        });


        categoryAdapter adapter = new categoryAdapter(this,categoryname,image);
        gridView.setAdapter(adapter);

    }







}