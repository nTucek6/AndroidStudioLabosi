package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class PersonalInfoActivity extends AppCompatActivity {


    public TextInputEditText TextBoxUnosName;
    public TextInputEditText TextBoxUnosSurname;
    public TextInputEditText TextBoxUnosDate;
    private Button oBtnNext;


    @Override
   public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);



        TextBoxUnosName = findViewById(R.id.textBoxInputName);
        TextBoxUnosSurname = findViewById(R.id.textBoxInputSurname);
        TextBoxUnosDate = findViewById(R.id.textBoxInputDate);
        oBtnNext = findViewById(R.id.btnRegister);

        oBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextBoxUnosName.length() != 0 && TextBoxUnosSurname.length() != 0 && TextBoxUnosDate.length() != 0)
                {
                    startActivity(new Intent(PersonalInfoActivity.this, StudentInfoActivity.class).
                            putExtra("ImeValue",TextBoxUnosName.getText().toString()).
                            putExtra("PrezimeValue",TextBoxUnosSurname.getText().toString()).
                            putExtra("DatumValue",TextBoxUnosDate.getText().toString()));
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