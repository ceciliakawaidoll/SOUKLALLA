package com.example.souklalla;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WomenProfile extends AppCompatActivity {
ImageView i1;
    String womenEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_profile);
        CardView BACK = findViewById(R.id.cd_back);
        // Retrieve email passed from previous activity
        womenEmail = getIntent().getStringExtra("women_email");
        String womenId = getIntent().getStringExtra("womenId");

        i1=findViewById(R.id.women_prof);
        FloatingActionButton f1=findViewById(R.id.BSelectI1);



        TextView Fullname=findViewById(R.id.women_fullname);
        TextView Email=findViewById(R.id.women_email);
        TextView Phone=findViewById(R.id.women_phone);



        String email = getIntent().getStringExtra("women_email");
        if (email == null) {
           // Toast.makeText(this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
// Reference to the "Users" node in the database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");

        // Query to find the user by email
        Query query = databaseReference.orderByChild("womenId").equalTo(womenId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Fetch and display user data
                        String name = userSnapshot.child("women_name").getValue(String.class);
                        String lastName = userSnapshot.child("women_lastn").getValue(String.class);
                        String phone = userSnapshot.child("women_phone").getValue(String.class);
                        String emaill = userSnapshot.child("women_email").getValue(String.class);

                        // Concatenate name and last name
                        String fullName = (name != null ? name : "") + " " + (lastName != null ? lastName : "");

                        Fullname.setText(fullName.trim().isEmpty() ? "غير متوفر" : fullName);
                        Email.setText(emaill != null ? emaill : "غير متوفر");
                        Phone.setText(phone != null ? phone : "غير متوفر");
                    }
                } else {
                    Toast.makeText(WomenProfile.this, "لا توجد بيانات لهذا المستخدم!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WomenProfile.this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(WomenProfile.this)
                        .crop()
                        .cropSquare()
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenProfile.this, WomenProfilehome.class);
            intent.putExtra("womenId", womenId);
            intent.putExtra("women_email", womenEmail);
            startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        i1.setImageURI(uri);
    }
}