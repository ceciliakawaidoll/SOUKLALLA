package com.example.souklalla;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserCreatacount extends AppCompatActivity {


    private EditText email, pass, pass2, name, lastname, phone;
    private Button signInButton;
    private Spinner wilayaSpinner;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_creatacount);

        initializeViews();

        // Setting up the spinner adapter
        setUpSpinner();

        signInButton.setOnClickListener(v -> registerUser());

        findViewById(R.id.cd_back).setOnClickListener(v -> {
            Intent intent = new Intent(UserCreatacount.this, USERCATEGORY.class);
            startActivity(intent);
        });
    }

    private void initializeViews() {
        email = findViewById(R.id.user_email);


        pass = findViewById(R.id.user_pass1);
        pass2 = findViewById(R.id.user_pass2);
        name = findViewById(R.id.user_name);
        lastname = findViewById(R.id.user_lastname);
        phone = findViewById(R.id.user_phone);
        wilayaSpinner = findViewById(R.id.user_spinner);
        signInButton = findViewById(R.id.btn72);
    }

    private void setUpSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.wilays)
        ) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0; // Disable the first item (hint)
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.LTGRAY : Color.BLACK);
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        wilayaSpinner.setAdapter(adapter);

        wilayaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // No action needed here for now
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    private void registerUser() {
        String emailInput = email.getText().toString().trim();
        String passInput = pass.getText().toString().trim();
        String pass2Input = pass2.getText().toString().trim();
        String nameInput = name.getText().toString().trim();
        String lastnameInput = lastname.getText().toString().trim();
        String phoneInput = phone.getText().toString().trim();
        String wilayaInput = wilayaSpinner.getSelectedItem().toString();
        String img= null;

        // Validation
        if (emailInput.isEmpty() || passInput.isEmpty() || pass2Input.isEmpty() || nameInput.isEmpty() ||
                lastnameInput.isEmpty() || phoneInput.isEmpty() || wilayaInput.equals("اختر ولاية")) {
            Toast.makeText(this, "يجب ملئ جميع البيانات", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passInput.equals(pass2Input)) {
            Toast.makeText(this, "كلمة السر غير متطابقة", Toast.LENGTH_SHORT).show();
            return;
        }

        // Storing user data in Firebase
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");

        String userId = ref.push().getKey(); // Generate unique user ID
        if (userId == null) {
            Toast.makeText(this, "حدث خطأ، يرجى المحاولة لاحقًا", Toast.LENGTH_SHORT).show();
            return;
        }

        user_helperclass helperClass = new user_helperclass(nameInput, phoneInput, lastnameInput, emailInput, wilayaInput, passInput, userId,img);
        ref.child(userId).setValue(helperClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "لقد قمت بالتسجيل بنجاح!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserCreatacount.this, USERHOMEPROFILE.class);
                        intent.putExtra("userId", userId);
                        intent.putExtra("user_email", emailInput);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "فشل التسجيل، حاول مرة أخرى", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
