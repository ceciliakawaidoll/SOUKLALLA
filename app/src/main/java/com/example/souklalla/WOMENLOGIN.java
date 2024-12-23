package com.example.souklalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class WOMENLOGIN extends AppCompatActivity {
    EditText wlemail, wlpass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_login);
        TextView text = findViewById(R.id.tv_acont);
        TextView textpass = findViewById(R.id.tv_pass);


        wlemail = findViewById(R.id.women_email);
        wlpass = findViewById(R.id.women_pass1);
        login = findViewById(R.id.button2);

        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENHELLO.class);
            startActivity(intent);
        });
       text.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENCREATACOUNT.class);
            startActivity(intent);
        });

        textpass.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENLOGIN.this, WOMENFORGOTPASS.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String Email = wlemail.getText().toString().trim();
            String Pass = wlpass.getText().toString().trim();

            if (Email.isEmpty() || Pass.isEmpty()) {
                Toast.makeText(WOMENLOGIN.this, "يجب ملء جميع البيانات", Toast.LENGTH_SHORT).show();
                return;
            }

            // Reference to the "Users" node in the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");

            // Query to find a user with the matching email
            Query query = databaseReference.orderByChild("women_email").equalTo(Email);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Iterate over the results (should be only one due to unique emails)
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String storedPassword = userSnapshot.child("women_pass").getValue(String.class);

                            // Check if the entered password matches the stored password
                            if (Objects.equals(storedPassword, Pass)) {
                                Toast.makeText(WOMENLOGIN.this, "تم تسجيل الدخول بنجاح!", Toast.LENGTH_SHORT).show();
                                // Navigate to the next activity
                                Intent intent = new Intent(WOMENLOGIN.this, WomenProfilehome.class);

                                intent.putExtra("women_email", Email);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(WOMENLOGIN.this, "كلمة المرور غير صحيحة!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(WOMENLOGIN.this, "البريد الإلكتروني غير موجود!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(WOMENLOGIN.this, "حدث خطأ أثناء تسجيل الدخول. حاول مرة أخرى!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}