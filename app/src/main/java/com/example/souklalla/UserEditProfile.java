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

public class UserEditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_edit_profile);

        // تعريف العناصر
        CardView BACK = findViewById(R.id.cd_back);
        Button button = findViewById(R.id.button7);
        EditText name = findViewById(R.id.name_ed);
        EditText email = findViewById(R.id.email_ed);
        EditText phone = findViewById(R.id.phone_ed);

        // الحصول على معرف المستخدم والبريد الإلكتروني من النية (Intent)
        String userEmail = getIntent().getStringExtra("user_email");
        String userid = getIntent().getStringExtra("userId");

        // التحقق إذا كان userId فارغًا
        if (userid == null || userid.isEmpty()) {
            Toast.makeText(this, "معرف المستخدم مفقود. لا يمكن المتابعة.", Toast.LENGTH_SHORT).show();
            finish(); // إنهاء النشاط
            return;
        }

        // عند النقر على زر التحديث
        button.setOnClickListener(v -> {
            // جلب القيم المحدثة من الحقول
            String name_ed = name.getText().toString().trim();
            String email_ed = email.getText().toString().trim();
            String phone_ed = phone.getText().toString().trim();

            // الإشارة إلى قاعدة بيانات Firebase
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            HashMap<String, Object> updates = new HashMap<>();

            // معالجة الحقل name_ed لتقسيمه إلى الاسم الأول واسم العائلة
            if (!name_ed.isEmpty()) {
                String firstName = "";
                String lastName = "";

                // تقسيم الاسم الكامل إلى الاسم الأول واسم العائلة
                String[] nameParts = name_ed.split("\\s+", 2); // تقسيم باستخدام المسافات، والحد الأقصى جزئين
                firstName = nameParts[0]; // الجزء الأول هو الاسم الأول
                if (nameParts.length > 1) {
                    lastName = nameParts[1]; // الجزء الثاني هو اسم العائلة
                }
                updates.put("user_name", firstName);
                updates.put("user_lastn", lastName);
            }

            // معالجة البريد الإلكتروني
            if (!email_ed.isEmpty()) {
                updates.put("user_email", email_ed); // تحديث البريد الإلكتروني
            }

            // معالجة رقم الهاتف
            if (!phone_ed.isEmpty()) {
                updates.put("user_phone", phone_ed); // تحديث رقم الهاتف
            }

            // تحديث معلومات المستخدم في Firebase
            databaseReference.child(userid).updateChildren(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(UserEditProfile.this, "تم تحديث الملف الشخصي بنجاح!", Toast.LENGTH_SHORT).show();
                        // إعادة التوجيه إلى الملف الشخصي
                        Intent intent = new Intent(UserEditProfile.this, USERHOMEPROFILE.class);
                        intent.putExtra("userId", userid);
                        intent.putExtra("user_email", userEmail);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(UserEditProfile.this, "فشل تحديث الملف الشخصي: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        // عند النقر على زر الرجوع
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(UserEditProfile.this, USERHOMEPROFILE.class);
            intent.putExtra("userId", userid);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);
        });
    }
}
