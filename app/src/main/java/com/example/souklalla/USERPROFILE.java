package com.example.souklalla;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class USERPROFILE extends AppCompatActivity {
      ImageView i2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        i2=findViewById(R.id.iv_user_prof);
        FloatingActionButton f2=findViewById(R.id.BSelectImage);
        CardView BACK = findViewById(R.id.cd_back);


        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(USERPROFILE.this)
                        .crop()
                        .cropSquare()
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERPROFILE.this, USERHOMEPROFILE.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        i2.setImageURI(uri);
    }
}