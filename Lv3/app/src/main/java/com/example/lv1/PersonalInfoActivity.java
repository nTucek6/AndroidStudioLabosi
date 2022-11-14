package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfoActivity extends AppCompatActivity {


    public TextInputEditText TextBoxUnosName;
    public TextInputEditText TextBoxUnosSurname;
    public TextInputEditText TextBoxUnosDate;
    private Button oBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        TextBoxUnosName = findViewById(R.id.textBoxInputName);
        TextBoxUnosSurname = findViewById(R.id.textBoxInputSurname);
        TextBoxUnosDate = findViewById(R.id.textBoxInputDate);
        oBtnNext = findViewById(R.id.btnRegister);

        Intent intent = getIntent();

        List<Student> studentList = intent.getParcelableArrayListExtra("listaStudent");


        oBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextBoxUnosName.length() != 0 && TextBoxUnosSurname.length() != 0 && TextBoxUnosDate.length() != 0)
                {
                    String name = TextBoxUnosName.getText().toString();
                    String surname = TextBoxUnosSurname.getText().toString();
                    String birthdate = TextBoxUnosDate.getText().toString();

                    Student student = new Student(name,surname,birthdate,0);

                    startActivity(new Intent(PersonalInfoActivity.this, StudentInfoActivity.class).
                            putExtra("Student", student).
                            putParcelableArrayListExtra("listaStudent", (ArrayList<? extends Parcelable>) studentList)
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