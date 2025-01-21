package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
        ImageView noti= findViewById(R.id.iv_notifi);

        womenEmail = getIntent().getStringExtra("women_email");


        RecyclerView category_list = findViewById(R.id.rv_product_home);


        List<product_helperclass> category = new ArrayList<>();

        categoryAdd_Adapter addAdapter = new categoryAdd_Adapter(category, WOMENHOMEPAGE.this, womenEmail);
        category_list.setAdapter(addAdapter);
        category_list.setLayoutManager(new LinearLayoutManager(this));








        ImageView profile= findViewById(R.id.iv_profile);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");

        // Query to find the user by email
        Query query = databaseReference.orderByChild("women_email").equalTo(womenEmail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Fetch and display user data
                        String name = userSnapshot.child("women_name").getValue(String.class);
                        String lastName = userSnapshot.child("women_lastn").getValue(String.class);

                        // Concatenate name and last name
                        fullName = (name != null ? name : "") +" "+ (lastName != null ? lastName :"");




                    }
                } else {
                 //  Toast.makeText(WOMENHOMEPAGE.this, "لا توجد بيانات لهذا المستخدم!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(PRODUCTADD.this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference databaseReferenc = FirebaseDatabase.getInstance().getReference("Products");
        Query quer = databaseReferenc.orderByChild("prod_wname").equalTo("j i");


        quer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                category.clear(); // Clear the list before adding new data
                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        product_helperclass product = productSnapshot.getValue(product_helperclass.class);
                        if (product != null) {
                            category.add(product);
                        }
                    }
                }
                addAdapter.notifyDataSetChanged(); // Notify the adapter about the data change, even if the list is empty
                if (category.isEmpty()) {
                   // Toast.makeText(WOMENHOMEPAGE.this, "No products found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WOMENHOMEPAGE.this, "Error fetching products.", Toast.LENGTH_SHORT).show();
            }
        });





        ft.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, PRODUCTADD.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenProfilehome.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

        noti.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENHOMEPAGE.this, WomenNotification.class);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

    }
}