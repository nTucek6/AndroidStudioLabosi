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


    public TextInputEditText TextBoxUnos;
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



        TextBoxUnos = findViewById(R.id.textBoxUnos);
        oBtnNext = findViewById(R.id.btnRegister);

        oBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextBoxUnos.length() != 0)
                {
                    startActivity(new Intent(PersonalInfoActivity.this, StudentInfoActivity.class).
                            putExtra("ImeValue",TextBoxUnos.getText().toString())); //Intent.EXTRA_TEXT
                }
                else
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Unesite ime!",Toast. LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }
}