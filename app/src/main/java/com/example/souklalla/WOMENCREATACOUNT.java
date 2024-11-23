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
import android.widget.Spinner;
import android.widget.TextView;

public class WOMENCREATACOUNT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.women_creatacount);

        Spinner spin = findViewById(R.id.spinner);
        Button bt=findViewById(R.id.btn72);
        TextView tv=findViewById(R.id.textView20);
        CardView BACK = findViewById(R.id.cd_back);
        BACK.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENCREATACOUNT.this, WOMENLOGIN.class);
            startActivity(intent);
        });


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          String item = parent.getItemAtPosition(position).toString();

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
          });

        getResources().getStringArray(R.array.wilays);





        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.wilays)){



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
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);








        bt.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENCREATACOUNT.this, WOMENHOMEPAGE.class);
            startActivity(intent);
        });


        tv.setOnClickListener(v -> {
            Intent intent = new Intent(WOMENCREATACOUNT.this, TERMSWOMEN.class);
            startActivity(intent);
        });





    }
}