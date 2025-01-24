package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WomenEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.women_edit_profile);

        CardView BACK = findViewById(R.id.cd_back);

        Button button = findViewById(R.id.button7);
        EditText women_ed = findViewById(R.id.women_ed);
        EditText women_em = findViewById(R.id.emailw_ed);
        EditText women_ph = findViewById(R.id.phonew_ed);

        String Email = getIntent().getStringExtra("women_email");
        String womenId = getIntent().getStringExtra("womenId");
        button.setOnClickListener(v -> {

            String namew_ed = women_ed.getText().toString().trim();
            String emailw_ed = women_em.getText().toString().trim();
            String phonew_ed = women_ph.getText().toString().trim();
            // الإشارة إلى قاعدة بيانات Firebase
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");
            HashMap<String, Object> updates = new HashMap<>();

            // معالجة الحقل name_ed لتقسيمه إلى الاسم الأول واسم العائلة
            if (!namew_ed.isEmpty()) {
                String firstName = "";
                String lastName = "";

                // تقسيم الاسم الكامل إلى الاسم الأول واسم العائلة
                String[] nameParts = namew_ed.split("\\s+", 2); // تقسيم باستخدام المسافات، والحد الأقصى جزئين
                firstName = nameParts[0]; // الجزء الأول هو الاسم الأول
                if (nameParts.length > 1) {
                    lastName = nameParts[1]; // الجزء الثاني هو اسم العائلة
                }
                updates.put("women_name", firstName);
                updates.put("women_lastn", lastName);
            }

            // معالجة البريد الإلكتروني
            if (!emailw_ed.isEmpty()) {
                updates.put("women_email", emailw_ed); // تحديث البريد الإلكتروني
            }

            // معالجة رقم الهاتف
            if (!phonew_ed.isEmpty()) {
                updates.put("women_phone", phonew_ed); // تحديث رقم الهاتف
            }

            // تحديث معلومات المستخدم في Firebase
            databaseReference.child(womenId).updateChildren(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(WomenEditProfile.this, "تم تحديث الملف الشخصي بنجاح!", Toast.LENGTH_SHORT).show();
                        // إعادة التوجيه إلى الملف الشخصي

                        Intent intent = new Intent(WomenEditProfile.this, WomenProfilehome.class);
                        intent.putExtra("women_email", Email);
                        intent.putExtra("womenId", womenId);
                        startActivity(intent);

                    })
                    .addOnFailureListener(e -> {
                       // Toast.makeText(UserEditProfile.this, "فشل تحديث الملف الشخصي: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

        });
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WomenEditProfile.this, WomenProfilehome.class);
            intent.putExtra("women_email", Email);
            intent.putExtra("womenId", womenId);
            startActivity(intent);
        });
    }
}