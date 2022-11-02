package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    public TextInputEditText TextBoxProfesor;
    public TextInputEditText TextBoxUnosPredmet;
    public TextInputEditText TextBoxUnosGodina;
    public TextInputEditText TextBoxUnosPredavanja;
    public TextInputEditText TextBoxUnosVjezbe;
    private Button oBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        TextBoxProfesor = findViewById(R.id.ProfesorId);
        TextBoxUnosPredmet = findViewById(R.id.PredmetId);
        TextBoxUnosGodina = findViewById(R.id.GodinaId);
        TextBoxUnosPredavanja = findViewById(R.id.PredavanjaSatiId);
        TextBoxUnosVjezbe = findViewById(R.id.VjezbeSatiId);
        oBtnNext = findViewById(R.id.btnNext);

        Intent intent = getIntent();
        String ImeValue = intent.getStringExtra("ImeValue");
        String PrezimeValue = intent.getStringExtra("PrezimeValue");
        String DatumValue = intent.getStringExtra("DatumValue");

        oBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextBoxUnosPredmet.length() != 0 && TextBoxProfesor.length() != 0 && TextBoxUnosGodina.length() != 0 && TextBoxUnosPredavanja.length() != 0 && TextBoxUnosVjezbe.length() != 0)
                {
                    startActivity(new Intent(StudentInfoActivity.this, SummaryActivity.class).
                            putExtra("ImeValue",ImeValue).
                            putExtra("PrezimeValue",PrezimeValue).
                            putExtra("DatumValue",DatumValue).
                            putExtra("PredmetValue",TextBoxUnosPredmet.getText().toString()).
                            putExtra("ProfesorValue",TextBoxProfesor.getText().toString()).
                            putExtra("GodinaValue",TextBoxUnosGodina.getText().toString()).
                            putExtra("PredavanjaValue",TextBoxUnosPredavanja.getText().toString()).
                            putExtra("VjezbeValue",TextBoxUnosVjezbe.getText().toString())
                    );
                }
                else
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Ispunite sva polja!",Toast. LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }
}