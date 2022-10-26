package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SummaryActivity extends AppCompatActivity {

    private TextView ViewIme;
    private TextView ViewPredmet;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ViewIme = findViewById(R.id.textViewIme);
        ViewPredmet = findViewById(R.id.textViewPredmet);
        btnRegister = findViewById(R.id.btnRegister);

        Intent intent = getIntent();
        String ImeValue = intent.getStringExtra("ImeValue");
        String  PredmetValue = intent.getStringExtra("PredmetValue");

        ViewIme.setText("Ime: " + ImeValue);
        ViewPredmet.setText("Predmet: "+ PredmetValue);


       btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                startActivity(new Intent(SummaryActivity.this, PersonalInfoActivity.class));
            }
        });

    }
}