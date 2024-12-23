package com.example.souklalla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PRODUCTADD extends AppCompatActivity {
    FloatingActionButton BSelectImage;
    ImageView IVPreviewImage;
    FirebaseDatabase database;
    DatabaseReference ref;


    public String fullName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_add);

        Spinner spinner = findViewById(R.id.prod_type);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.iv_addprod);
        TextView pname=findViewById(R.id.prod_name);
        TextView pdesc=findViewById(R.id.prod_desc);
        TextView pprice=findViewById(R.id.prod_price);
        Button add=findViewById(R.id.button7);

        CardView BACK = findViewById(R.id.cd_back);


        String email = getIntent().getStringExtra("women_email");
       if (email == null) {
            Toast.makeText(this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
// Reference to the "Users" node in the database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Womens");

        // Query to find the user by email
        Query query = databaseReference.orderByChild("women_email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Fetch and display user data
                        String name = userSnapshot.child("women_name").getValue(String.class);
                        String lastName = userSnapshot.child("women_lastn").getValue(String.class);

                        // Concatenate name and last name
                        fullName = (name != null ? name : "") + " " + (lastName != null ? lastName : "");

                    }
                } else {
                    Toast.makeText(PRODUCTADD.this, "لا توجد بيانات لهذا المستخدم!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PRODUCTADD.this, "حدث خطأ أثناء تحميل البيانات!", Toast.LENGTH_SHORT).show();
            }
        });






        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PRODUCTADD.this)
                        .crop()
                        .cropSquare()//Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String Pname = pname.getText().toString().trim();
                String Pdesc = pdesc.getText().toString().trim();
                String Pprice = pprice.getText().toString().trim();
                String Ptype = spinner.getSelectedItem().toString();


                Uri pimage= Uri.parse(IVPreviewImage.getImageMatrix().toString().trim());


                // Validate inputs
                if (Pname.isEmpty() || Pdesc.isEmpty() || Pprice.isEmpty()|| Ptype.equals("اختري صنفا") ) {
                    Toast.makeText(PRODUCTADD.this, "يجب ملئ جميع البيانات", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new unique ID for the user
                database = FirebaseDatabase.getInstance();
                ref = database.getReference("Products");
                DatabaseReference newUserRef = ref.push(); // Generate a unique ID for the new user
                String prodId = newUserRef.getKey(); // Get the generated ID

                // Create a new helper class instance with the women's data
                product_helperclass helperclass= new product_helperclass(Pname,pimage.toString(),Pprice,Pdesc,fullName,Ptype,prodId);

                // Store the data in Firebase
                newUserRef.setValue(helperclass);

                // Show success message
                Toast.makeText(PRODUCTADD.this, "تمت إضافة المنتج بنجاح!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PRODUCTADD.this, WOMENHOMEPAGE.class);
                intent.putExtra("women_email", email);
                startActivity(intent);
            }
        });



        // Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.pink)));

        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(PRODUCTADD.this, WOMENHOMEPAGE.class);
            intent.putExtra("women_email", email);
            startActivity(intent);
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
        Uri uri = data.getData();
        IVPreviewImage.setImageURI(uri);
        IVPreviewImage.setTag(uri.toString());
    }

}






