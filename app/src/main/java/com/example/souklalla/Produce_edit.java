package com.example.souklalla;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Produce_edit extends AppCompatActivity {
    FloatingActionButton BSelectImage;
    ImageView IVPreviewImage;
    FirebaseDatabase database;
    DatabaseReference ref;
    public String womenEmail;
    private Bitmap selectedImageBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.produce_edit);
        Spinner spinner = findViewById(R.id.prod_type);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.iv_addprod);
        TextView pname=findViewById(R.id.prod_name);
        TextView pdesc=findViewById(R.id.prod_desc);
        TextView pprice=findViewById(R.id.prod_price);
        Button etit=findViewById(R.id.button7);

        CardView BACK = findViewById(R.id.cd_back);
        womenEmail = getIntent().getStringExtra("women_email");
        String prodId = getIntent().getStringExtra("prod_id");


        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(Produce_edit.this, WOMENHOMEPAGE.class);
            intent.putExtra("women_email",womenEmail);
            startActivity(intent);
        });


        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Produce_edit.this)
                        .crop()
                        .cropSquare()//Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });




        etit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String Pname = pname.getText().toString().trim();
                String Pdesc = pdesc.getText().toString().trim();
                String Pprice = pprice.getText().toString().trim();
                String Ptype = spinner.getSelectedItem().toString();


                String base64Image = encodeImageToBase64(selectedImageBitmap);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");

            //prod_name,prod_price,prod_desc,prod_wname,prod_type,prod_img

                HashMap<String, Object> updates = new HashMap<>();
                if (!Pname.isEmpty()) {
                    updates.put("prod_name", Pname);
                }
                if (!Pdesc.isEmpty()) {
                    updates.put("prod_desc", Pdesc);
                }
                if (!Pprice.isEmpty()) {
                    updates.put("prod_price", Pprice);
                }
                if (!Ptype.equals("اختري صنفا")) {
                    updates.put("prod_type", Ptype);
                }
                if (base64Image != null) {
                    updates.put("prod_img", base64Image);
                }

                databaseReference.child(prodId).updateChildren(updates)
                        .addOnSuccessListener(aVoid -> {
                           // Toast.makeText(Produce_edit.this, "تم تعديل المنتج بنجاح", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(Produce_edit.this, "فشل التعديل: " + e.getMessage(), Toast.LENGTH_SHORT).show();



                        });

                Intent intent = new Intent(Produce_edit.this, WOMENHOMEPAGE.class);
                intent.putExtra("women_email",womenEmail);
                startActivity(intent);

            }
        });
















        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getResources().getStringArray(R.array.category);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category)) {


            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);


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