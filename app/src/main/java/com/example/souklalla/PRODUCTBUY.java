package com.example.souklalla;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PRODUCTBUY extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_buy);
        Button buy = findViewById(R.id.btn_login_W);
        ImageView plus= findViewById(R.id.iv_plus);
        ImageView minus= findViewById(R.id.iv_minus);
        ImageView im= findViewById(R.id.LOGO);
        TextView price= findViewById(R.id.tv_price);
        TextView number = findViewById(R.id.tv_number);
        TextView name = findViewById(R.id.tv_namep);
        TextView desc = findViewById(R.id.tv_desc_p);
        CardView back =findViewById(R.id.cd_back);



        Intent inten = getIntent();
        String prodName = inten.getStringExtra("prod_name");
        String prodDesc = inten.getStringExtra("prod_desc");
        String prodPrice = inten.getStringExtra("prod_price");
        String prodImg = inten.getStringExtra("prod_img");

        // Set the data into the UI elements
        name.setText(prodName);
        desc.setText(prodDesc);
        price.setText(prodPrice);

        // Decode the Base64 image string and set it to ImageView
        if (prodImg != null) {
            byte[] decodedString = Base64.decode(prodImg, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            im.setImageBitmap(decodedBitmap);
        } else {
            im.setImageResource(R.drawable.photoo);
        }

        // Set initial quantity to 1
        number.setText("1");





        back.setOnClickListener(v -> {
            Intent intent = new Intent(PRODUCTBUY.this, USERCATEGORY.class);
            startActivity(intent);
        });
        buy.setOnClickListener(v -> {
            Intent intent = new Intent(PRODUCTBUY.this, Payment.class);
            startActivity(intent);
        });



        plus.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());
            number.setText(String.valueOf(num+1));
            double totalPrice = num * Double.parseDouble(prodPrice);
            price.setText(String.format("%.2f", totalPrice));
        });
        minus.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());


            if (num > 1) {
                number.setText(String.valueOf(num - 1));
                double totalPrice = (num - 1) * Double.parseDouble(prodPrice);
                price.setText(String.format("%.2f", totalPrice));
            } else {
                //Toast.makeText(getApplicationContext(), "Cannot go below 0", Toast.LENGTH_SHORT).show();
            }
        });


    }
}