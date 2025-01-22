package com.example.souklalla;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WOMENHOMEPAGE extends AppCompatActivity {
    String womenEmail;
    public String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_homepage);

        FloatingActionButton ft = findViewById(R.id.flotadd);
        ImageView noti = findViewById(R.id.iv_notifi);
        ImageView profile = findViewById(R.id.iv_profile);
        ProgressBar progressBar = findViewById(R.id.progressBar2);

        ColorStateList colorStateList = ContextCompat.getColorStateList(WOMENHOMEPAGE.this, R.color.burgundy);
        progressBar.setIndeterminateTintList(colorStateList);
        progressBar.setVisibility(View.VISIBLE);

        womenEmail = getIntent().getStringExtra("women_email");

        RecyclerView category_list = findViewById(R.id.rv_product_home);
        List<product_helperclass> category = new ArrayList<>();
        categoryAdd_Adapter addAdapter = new categoryAdd_Adapter(category, WOMENHOMEPAGE.this, womenEmail);

        category_list.setAdapter(addAdapter);
        category_list.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");
        Query query = databaseReference.orderByChild("women_email").equalTo(womenEmail);

        // Fetch user data and products
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String name = userSnapshot.child("women_name").getValue(String.class);
                        String lastName = userSnapshot.child("women_lastn").getValue(String.class);

                        fullName = (name != null ? name : "") + " " + (lastName != null ? lastName : "");
                        fullName = fullName.trim(); // Remove extra spaces

                       // Toast.makeText(WOMENHOMEPAGE.this, fullName, Toast.LENGTH_SHORT).show();
                        Log.d("WOMENHOMEPAGE", "FullName: " + fullName);

                        // Fetch products once fullName is available
                        fetchProducts(fullName, category, addAdapter, progressBar);
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(WOMENHOMEPAGE.this, "No user found with the provided email.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WOMENHOMEPAGE.this, "Error fetching user data.", Toast.LENGTH_SHORT).show();
            }
        });

        // Floating Action Button for adding a product
        ft.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, PRODUCTADD.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        // Profile button click listener
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenProfilehome.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        // Notification button click listener
        noti.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenNotification.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });
    }

    /**
     * Fetch products associated with the user's full name.
     */
    private void fetchProducts(String fullName, List<product_helperclass> category,
                               categoryAdd_Adapter addAdapter, ProgressBar progressBar) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        Query productQuery = databaseReference.orderByChild("prod_wname").equalTo(fullName);

        productQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                category.clear(); // Clear the list before adding new data
                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        product_helperclass product = productSnapshot.getValue(product_helperclass.class);
                        if (product != null) {
                            category.add(product);
                        }
                    }
                }
                addAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
                if (category.isEmpty()) {
                   // Toast.makeText(WOMENHOMEPAGE.this, "لا توجد منتوجات", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WOMENHOMEPAGE.this, "Error fetching products.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
