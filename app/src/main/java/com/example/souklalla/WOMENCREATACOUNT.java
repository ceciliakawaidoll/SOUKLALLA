package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WOMENCREATACOUNT extends AppCompatActivity {

    EditText email, pass, pass2, name, lastname, phone;
    Button signin;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_creatacount);

        // Initialize UI elements
        Spinner spin = findViewById(R.id.spinner);
        signin = findViewById(R.id.btn72);
        TextView tv = findViewById(R.id.textView20);
        CardView BACK = findViewById(R.id.cd_back);
        CheckBox checkBox = findViewById(R.id.checkBox);

        // Initialize EditText fields
        email = findViewById(R.id.women_email);
        pass = findViewById(R.id.women_pass1);
        pass2 = findViewById(R.id.women_pass2);
        name = findViewById(R.id.women_name);
        lastname = findViewById(R.id.women_lastname);
        phone = findViewById(R.id.women_phone);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String Email = email.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                String Pass2 = pass2.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Lastname = lastname.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String wilaya = spin.getSelectedItem().toString();
                boolean isCheck = checkBox.isChecked();

                // Validate inputs
                if (Email.isEmpty() || Pass.isEmpty() || Pass2.isEmpty() || Name.isEmpty() || Lastname.isEmpty() || Phone.isEmpty() || wilaya.equals("اختر ولاية")) {
                    Toast.makeText(WOMENCREATACOUNT.this, "يجب ملئ جميع البيانات", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isCheck) {
                    Toast.makeText(WOMENCREATACOUNT.this, "يجب الموافقة على الشروط والاحكام", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Pass.equals(Pass2)) {
                    Toast.makeText(WOMENCREATACOUNT.this, "كلمة السر غير متطابقة", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new unique ID for the user
                database = FirebaseDatabase.getInstance();
                ref = database.getReference("Womens");
                DatabaseReference newUserRef = ref.push(); // Generate a unique ID for the new user
                String womenId = newUserRef.getKey(); // Get the generated ID

                // Create a new helper class instance with the women's data
                women_helperclass helperclass = new women_helperclass(Name, Pass, wilaya, Email, Phone, Lastname, womenId);

                // Store the data in Firebase
                newUserRef.setValue(helperclass);

                // Show success message
                Toast.makeText(WOMENCREATACOUNT.this, "لقد قمت بالتسجيل بنجاح!", Toast.LENGTH_SHORT).show();

                // Redirect to WOMENHOMEPAGE
                Intent intent = new Intent(WOMENCREATACOUNT.this, WOMENHOMEPAGE.class);
                intent.putExtra("womenId", womenId);
                intent.putExtra("women_email", Email);
                startActivity(intent);
            }
        });

        // Navigate to login page on back button click
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENCREATACOUNT.this, WOMENLOGIN.class);
            startActivity(intent);
        });

        // Set up the spinner
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get selected item (Wilaya)
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected
            }
        });

        // Adapter for Spinner (Wilayas)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.wilays)) {
            @Override
            public boolean isEnabled(int position) {
                // Disable the first item (hint)
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                // Set hint text color to gray
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // Navigate to terms page when clicking on the terms text
        tv.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENCREATACOUNT.this, TERMSWOMEN.class);
            startActivity(intent);
        });
    }
}
