package com.example.lv4;

import static com.example.lv4.Class.JsonManipulator.LoadJson;
import static com.example.lv4.Class.JsonManipulator.jsonStringToObjectSummary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lv4.Class.SaveSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private final static String  FILE_SUMMARY = "SummaryInfo.json";

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    StudentAdapter adapter;
    List<SaveSummary> summaryList = new ArrayList<>();

    Spinner spinner;
    Locale locale;
    String currentLanguage = "en", currentLang;
    Button newStudentBtn;
    TextView StudentText;


    @Override
    public void onBackPressed()
    {
       // moveTaskToBack(true);
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.ExitDialog))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
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

        LoadJSONSummary();

        newStudentBtn = findViewById(R.id.AddNewStudentBtn);
        StudentText = findViewById(R.id.tvHeader);
        newStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateNewRecordActivity.class));
            }
        });

       if(summaryList != null)
       {
           if(!summaryList.isEmpty())
           {
               recyclerView = findViewById(R.id.recyclerView);
               //progressBar = findViewById(R.id.progressBar);
               layoutManager = new LinearLayoutManager(this);
               recyclerView.setLayoutManager(layoutManager);
               // adapter = new StudentAdapter(studentList,subjectList);
               adapter = new StudentAdapter(summaryList);
               recyclerView.setAdapter(adapter);
           }
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


    private void LoadJSONSummary()
    {
        String json = LoadJson(MainActivity.this,FILE_SUMMARY);
        if(json != null)
        {
           summaryList = jsonStringToObjectSummary(json);
        }
    }

}