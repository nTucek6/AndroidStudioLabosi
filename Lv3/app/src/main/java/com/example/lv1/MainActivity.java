package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    StudentAdapter adapter;
    List<Student> studentList = new ArrayList<>();
    List<Subject> subjectList = new ArrayList<>();

    Spinner spinner;
    Locale locale;
    String currentLanguage = "en", currentLang;
    Button newStudentBtn;
    TextView StudentText;

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLanguage = getIntent().getStringExtra(currentLang);
        spinner = findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.SpinnerData)));

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hr");
                       // setLocale(parent.getItemAtPosition(position).toString());
                        break;
                    case 3:
                        setLocale("hu");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        newStudentBtn = findViewById(R.id.AddNewStudentBtn);
        StudentText = findViewById(R.id.tvHeader);
        newStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PersonalInfoActivity.class).
                        putParcelableArrayListExtra("listaStudent", (ArrayList<? extends Parcelable>) studentList));
            }
        });

        Intent i = getIntent();

        if(i.hasExtra("listaStudent"))
        {
            StudentText.setText(getString(R.string.studenti));
            studentList = i.getParcelableArrayListExtra("listaStudent");
        }
        if(i.hasExtra("Student") && i.hasExtra("Subject"))
        {
            studentList.add(i.getParcelableExtra("Student"));
            subjectList.add(i.getParcelableExtra("Subject"));
        }
        if(!studentList.isEmpty())
        {
            recyclerView = findViewById(R.id.recyclerView);
            //progressBar = findViewById(R.id.progressBar);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new StudentAdapter(studentList,subjectList);
            recyclerView.setAdapter(adapter);
        }
    }
    private void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            locale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this,
                    MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}