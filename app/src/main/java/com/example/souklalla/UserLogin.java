package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserLogin extends AppCompatActivity {
    EditText ulemail, ulpass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_login);

        ulemail = findViewById(R.id.user_email);
        ulpass = findViewById(R.id.user_pass1);
        login = findViewById(R.id.user_login);

        TextView text = findViewById(R.id.user_acont);
        TextView textpass = findViewById(R.id.user_forgpass);

        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(UserLogin.this, USERCATEGORY.class);
            startActivity(intent);
        });

        text.setOnClickListener(v -> {
            Intent intent = new Intent(UserLogin.this, UserCreatacount.class);
            startActivity(intent);
        });

        textpass.setOnClickListener(v -> {
            Intent intent = new Intent(UserLogin.this, UserForgotpass.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String Email = ulemail.getText().toString().trim();
            String Pass = ulpass.getText().toString().trim();

            if (Email.isEmpty() || Pass.isEmpty()) {
                Toast.makeText(UserLogin.this, "يجب ملء جميع البيانات", Toast.LENGTH_SHORT).show();
                return;
            }

            // Reference to the "Users" node in the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

            // Query to find a user with the matching email
            Query query = databaseReference.orderByChild("user_email").equalTo(Email);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Iterate over the results (should be only one due to unique emails)
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String storedPassword = userSnapshot.child("user_pass").getValue(String.class);

                            // Check if the entered password matches the stored password
                            if (Objects.equals(storedPassword, Pass)) {
                                Toast.makeText(UserLogin.this, "تم تسجيل الدخول بنجاح!", Toast.LENGTH_SHORT).show();
                                // Navigate to the next activity
                                Intent intent = new Intent(UserLogin.this, USERHOMEPROFILE.class);

                                intent.putExtra("user_email", Email);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(UserLogin.this, "كلمة المرور غير صحيحة!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(UserLogin.this, "البريد الإلكتروني غير موجود!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UserLogin.this, "حدث خطأ أثناء تسجيل الدخول. حاول مرة أخرى!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}