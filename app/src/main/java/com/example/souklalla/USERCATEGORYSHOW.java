package com.example.souklalla;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class USERCATEGORYSHOW extends AppCompatActivity {
 String prodtyp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_categoryshow);

        CardView BACK = findViewById(R.id.cd_back);
        RecyclerView category_list = findViewById(R.id.rv_product);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ColorStateList colorStateList = ContextCompat.getColorStateList(USERCATEGORYSHOW.this, R.color.burgundy);
        progressBar.setIndeterminateTintList(colorStateList);
        progressBar.setVisibility(View.VISIBLE);

        // Getting the type of products passed through Intent

        String prodtype = getIntent().getStringExtra("type");

        String[] categoryy = new String[]{
                getIntent().getStringExtra("type1"),
                getIntent().getStringExtra("type2"),
                getIntent().getStringExtra("type3"),
                getIntent().getStringExtra("type4"),
                getIntent().getStringExtra("type5"),
                getIntent().getStringExtra("type6"),
                getIntent().getStringExtra("type7"),
        };

            List<product_helperclass> category = new ArrayList<>();
            // Setting up the adapter and RecyclerView
            Category_Adapter adapter = new Category_Adapter(category, USERCATEGORYSHOW.this);
            category_list.setAdapter(adapter);
            category_list.setLayoutManager(new LinearLayoutManager(USERCATEGORYSHOW.this));


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
            Query query = databaseReference.orderByChild("prod_type").equalTo(prodtype);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    if (dataSnapshot.exists()) {
                        // Toast.makeText(USERCATEGORYSHOW.this, "تم العثور على البيانات", Toast.LENGTH_SHORT).show();
                        // Loop through the fetched products and add them to the list
                        for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                            product_helperclass product = productSnapshot.getValue(product_helperclass.class);
                            if (product != null) {
                                category.add(product); // Add the product to the list
                            }
                        }
                        // Notify the adapter that the data has changed so it can refresh the RecyclerView
                        adapter.notifyDataSetChanged();
                        //progressBar.setVisibility(View.VISIBLE);

                    } else {
                        //Toast.makeText(USERCATEGORYSHOW.this, "لم يتم العثور على المنتوجات", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(USERCATEGORYSHOW.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                   // progressBar.setVisibility(View.VISIBLE);
                }
            });



        for (String cat : categoryy) {
            if (cat == null || cat.isEmpty()) {
                continue; // Skip null or empty categories
            }

            switch (cat) {
                case "الأطباق":
                    prodtyp = "الأطباق";
                    break;
                case "الحلويات":
                    prodtyp = "الحلويات";
                    break;
                case "بلانر":
                    prodtyp = "بلانر";
                    break;
                case "الخياطة":
                    prodtyp = "الخياطة";
                    break;
                case "الحياكة":
                    prodtyp = "الحياكة";
                    break;
                case "الفخار":
                    prodtyp = "الفخار";
                    break;

                case "المناسبات":
                    prodtyp = "المناسبات";
                    break;

                default:
                    prodtyp = null;


            }

            if (prodtyp != null) {
                // Reference to the Firebase database
                DatabaseReference databaseReferenc = FirebaseDatabase.getInstance().getReference("Products");
                Query quer = databaseReferenc.orderByChild("prod_type").equalTo(prodtyp);


                quer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        progressBar.setVisibility(View.GONE);
                        if (dataSnapshot.exists()) {
                            // Toast.makeText(USERCATEGORYSHOW.this, "تم العثور على البيانات", Toast.LENGTH_SHORT).show();
                            // Loop through the fetched products and add them to the list
                            for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                                product_helperclass product = productSnapshot.getValue(product_helperclass.class);
                                if (product != null) {
                                    category.add(product); // Add the product to the list
                                }
                            }
                            // Notify the adapter that the data has changed so it can refresh the RecyclerView
                            adapter.notifyDataSetChanged();

                        } else {
                           // Toast.makeText(USERCATEGORYSHOW.this, "لم يتم العثور على المنتوجات", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(USERCATEGORYSHOW.this, "Error fetching data", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }



            // Handling the back button click
            BACK.setOnClickListener(v -> {
                Intent intent = new Intent(USERCATEGORYSHOW.this, USERCATEGORY.class);
                startActivity(intent);
            });

        }
    }

