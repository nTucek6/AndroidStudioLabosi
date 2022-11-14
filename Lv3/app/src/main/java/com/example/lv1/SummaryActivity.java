package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        Student student =  intent.getParcelableExtra("Student");
        Subject subject =  intent.getParcelableExtra("Subject");

        List<Student> studentList = intent.getParcelableArrayListExtra("listaStudent");

        String ImeValue = student.getName();
        String PrezimeValue = student.getSurname();
        String DatumValue = student.getBirthDate();
        String  ProfesorValue = subject.getProfessor();
        String  PredmetValue = subject.getSubject();
        String  GodinaValue = String.valueOf(subject.getAcademicYear());
        String  PredavanjaValue = String.valueOf(subject.getClassesHours());
        String  VjezbeValue = String.valueOf(subject.getPracticeHours());

        ViewIme.setText(getString(R.string.ime)+": " + ImeValue);
        ViewPrezime.setText(getString(R.string.prezime)+": "+  PrezimeValue);
        ViewDatum.setText(getString(R.string.datum_rodjenja)+": "+ DatumValue);
        ViewPredmet.setText(getString(R.string.predmet)+": "+ PredmetValue);
        ViewProfesor.setText(getString(R.string.profesor)+": "+ ProfesorValue);
        ViewGodina.setText(getString(R.string.godina)+": "+GodinaValue);
        ViewPredavanja.setText(getString(R.string.predavanja)+": " + PredavanjaValue);
        ViewVjezbe.setText(getString(R.string.vjezbe)+": " + VjezbeValue);


       btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                startActivity(new Intent(SummaryActivity.this, MainActivity.class).
                        putExtra("Student",student).
                        putExtra("Subject",subject).
                        putParcelableArrayListExtra("listaStudent", (ArrayList<? extends Parcelable>) studentList)
                );
                finish();
            }
        });

    }
}