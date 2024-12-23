package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_categoryshow);

        CardView BACK = findViewById(R.id.cd_back);
        RecyclerView category_list = findViewById(R.id.rv_product);

        // Getting the type of products passed through Intent
        Intent inten = getIntent();
        String prodtype = inten.getStringExtra("type");

        List<product_helperclass> category = new ArrayList<>();


        // Setting up the adapter and RecyclerView
        Category_Adapter adapter = new Category_Adapter(category, USERCATEGORYSHOW.this);
        category_list.setAdapter(adapter);
        category_list.setLayoutManager(new LinearLayoutManager(USERCATEGORYSHOW.this));

        // Reference to the Firebase database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        Query query = databaseReference.orderByChild("prod_type").equalTo(prodtype);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(USERCATEGORYSHOW.this, "تم العثور على البيانات", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(USERCATEGORYSHOW.this, "No products found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(USERCATEGORYSHOW.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        // Handling the back button click
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(USERCATEGORYSHOW.this, USERCATEGORY.class);
            startActivity(intent);
        });

    }
}
