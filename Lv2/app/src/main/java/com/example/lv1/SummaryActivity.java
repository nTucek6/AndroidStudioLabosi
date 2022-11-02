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
    private TextView ViewPrezime;
    private TextView ViewDatum;
    private TextView ViewPredmet;
    private TextView ViewProfesor;
    private TextView ViewGodina;
    private TextView ViewPredavanja;
    private TextView ViewVjezbe;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ViewIme = findViewById(R.id.textViewIme);
        ViewPrezime = findViewById(R.id.textViewPrezime);
        ViewDatum = findViewById(R.id.textViewDatum);
        ViewPredmet = findViewById(R.id.textViewPredmet);
        ViewProfesor = findViewById(R.id.textViewProfesor);
        ViewGodina = findViewById(R.id.textViewGodina);
        ViewPredavanja = findViewById(R.id.textViewPredavanja);
        ViewVjezbe = findViewById(R.id.textViewVjezbe);
        btnRegister = findViewById(R.id.btnRegister);

        Intent intent = getIntent();
        String ImeValue = intent.getStringExtra("ImeValue");
        String PrezimeValue = intent.getStringExtra("PrezimeValue");
        String DatumValue = intent.getStringExtra("DatumValue");
        String  ProfesorValue = intent.getStringExtra("ProfesorValue");
        String  PredmetValue = intent.getStringExtra("PredmetValue");
        String  GodinaValue = intent.getStringExtra("GodinaValue");
        String  PredavanjaValue = intent.getStringExtra("PredavanjaValue");
        String  VjezbeValue = intent.getStringExtra("VjezbeValue");

        ViewIme.setText("Ime: " + ImeValue);
        ViewPrezime.setText("Prezime: " +  PrezimeValue);
        ViewDatum.setText("Datum rođenja: "+ DatumValue);
        ViewPredmet.setText("Predmet: "+ PredmetValue);
        ViewProfesor.setText("Profesor: "+ ProfesorValue);
        ViewGodina.setText("Akademska godina: " +GodinaValue);
        ViewPredavanja.setText("Broj sati predavnja: " + PredavanjaValue);
        ViewVjezbe.setText("Broj sati vježbi: " + VjezbeValue);


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