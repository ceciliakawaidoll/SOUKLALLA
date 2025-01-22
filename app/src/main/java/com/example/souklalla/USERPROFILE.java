package com.example.souklalla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.TelephonyNetworkSpecifier;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class USERPROFILE extends AppCompatActivity {
    FloatingActionButton BSelectImage;
    ImageView IVPreviewImage;
    String userEmail;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        IVPreviewImage=findViewById(R.id.iv_user_prof);
        BSelectImage=findViewById(R.id.BSelectImage);

        userEmail = getIntent().getStringExtra("user_email");
        CardView BACK = findViewById(R.id.cd_back);
        TextView Fullname=findViewById(R.id.user_fullname);
        TextView Email=findViewById(R.id.user_email);
        TextView Phone=findViewById(R.id.user_phone);



        String email = getIntent().getStringExtra("user_email");
        if (email == null) {
            Toast.makeText(this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
// Reference to the "Users" node in the database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Query to find the user by email
        Query query = databaseReference.orderByChild("user_email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Fetch and display user data
                        String name = userSnapshot.child("user_name").getValue(String.class);
                        String lastName = userSnapshot.child("user_lastn").getValue(String.class);
                        String phone = userSnapshot.child("user_phone").getValue(String.class);
                        String photo = userSnapshot.child("user_img").getValue(String.class);

                        // Concatenate name and last name
                        String fullName = (name != null ? name : "") + " " + (lastName != null ? lastName : "");

                        Fullname.setText(fullName.trim().isEmpty() ? "غير متوفر" : fullName);
                        Email.setText(email);
                        Phone.setText(phone != null ? phone : "غير متوفر");
                        if (photo != null) {
                            byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            IVPreviewImage.setImageBitmap(decodedBitmap);
                        }
                    }
                } else {
                    Toast.makeText(USERPROFILE.this, "لا توجد بيانات لهذا المستخدم!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(USERPROFILE.this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            }
        });

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(USERPROFILE.this)
                        .crop()
                        .cropSquare()
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();


                String base64Image = encodeImageToBase64(selectedImageBitmap);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                HashMap<String, Object> updates = new HashMap<>();
                if (base64Image != null) {
                    updates.put("user_img", base64Image);
                }
                databaseReference.child(userEmail).updateChildren(updates)
                        .addOnSuccessListener(aVoid -> {
                            // Toast.makeText(Produce_edit.this, "تم تعديل المنتج بنجاح", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(USERPROFILE.this, "فشل التعديل: " + e.getMessage(), Toast.LENGTH_SHORT).show();


                        });
            }
        });

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(USERPROFILE.this, USERHOMEPROFILE.class);
                intent.putExtra("user_email", userEmail);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            IVPreviewImage.setImageURI(uri);
            selectedImageBitmap = getBitmapFromUri(uri); // Convert Uri to Bitmap
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String encodeImageToBase64(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
