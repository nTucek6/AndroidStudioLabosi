package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    public TextInputEditText TextBoxUnos;
    private Button oBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        TextBoxUnos = findViewById(R.id.PredmetId);
        oBtnNext = findViewById(R.id.btnNext);

        Intent intent = getIntent();
        String ImeValue = intent.getStringExtra("ImeValue");

        oBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextBoxUnos.length() != 0)
                {
                    startActivity(new Intent(StudentInfoActivity.this, SummaryActivity.class).
                            putExtra("ImeValue",ImeValue).
                            putExtra("PredmetValue",TextBoxUnos.getText().toString()));
                }
                else
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Unesite predmet!",Toast. LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }
}