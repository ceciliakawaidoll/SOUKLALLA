package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
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
        TextView price= findViewById(R.id.tv_price);

        TextView number = findViewById(R.id.tv_number);
        TextView name = findViewById(R.id.tv_namep);
        TextView desc = findViewById(R.id.tv_desc_p);
        CardView back =findViewById(R.id.cd_back);


        back.setOnClickListener(v -> {
            Intent intent = new Intent(PRODUCTBUY.this, USERCATEGORY.class);
            startActivity(intent);
        });
        buy.setOnClickListener(v -> {
            Intent intent = new Intent(PRODUCTBUY.this, Payment.class);
            startActivity(intent);
        });

        Intent intent;
        intent = getIntent();
        String namep = intent.getStringExtra("product_name");
        name.setText("" + namep);
        String pric = intent.getStringExtra("prod_price");
        String descp = intent.getStringExtra("prod_desc");
        desc.setText("" + descp);

        plus.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());
            number.setText(String.valueOf(num+1));
            price.setText(String.valueOf((num+1)*1500));
        });
        minus.setOnClickListener(v -> {
            int num = Integer.parseInt(number.getText().toString());


            if (num > 1) {
                number.setText(String.valueOf(num - 1));
                price.setText(String.valueOf((num - 1) * 1500));
            } else {
                //Toast.makeText(getApplicationContext(), "Cannot go below 0", Toast.LENGTH_SHORT).show();
            }
        });


    }
}