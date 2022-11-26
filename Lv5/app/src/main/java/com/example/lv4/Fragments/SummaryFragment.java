package com.example.lv4.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lv4.CreateNewRecordActivity;
import com.example.lv4.FragmentDataClass.PersonalInfoData;
import com.example.lv4.FragmentDataClass.StudentInfoData;
import com.example.lv4.MainActivity;
import com.example.lv4.R;

public class SummaryFragment extends Fragment {

    public View rootView;

    private TextView ViewName;
    private TextView ViewSurname;
    private TextView ViewDate;
    private TextView ViewSubject;
    private TextView ViewProfessor;
    private TextView ViewYear;
    private TextView ViewClasses;
    private TextView ViewPractice;
    public Button btnRegister;
    private ImageView PreviewImage;

    private PersonalInfoData personalInfoData;
    private StudentInfoData studentInfoData;


    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewName = view.findViewById(R.id.textViewIme);
        ViewSurname = view.findViewById(R.id.textViewPrezime);
        ViewDate = view.findViewById(R.id.textViewDatum);
        ViewSubject = view.findViewById(R.id.textViewPredmet);
        ViewProfessor = view.findViewById(R.id.textViewProfesor);
        ViewYear = view.findViewById(R.id.textViewGodina);
        ViewClasses = view.findViewById(R.id.textViewPredavanja);
        ViewPractice = view.findViewById(R.id.textViewVjezbe);
        PreviewImage = view.findViewById(R.id.previewImage);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setEnabled(false);

    }

    public void SetSummaryData(PersonalInfoData personalInfoClass,StudentInfoData studentInfoClass)
    {
        personalInfoData =  personalInfoClass;
        studentInfoData = studentInfoClass;

        if(personalInfoClass != null && studentInfoClass != null) {
            if (personalInfoClass.CheckEmpty() && studentInfoClass.CheckEmpty()) {
                String NameValue = personalInfoData.getName();
                String SurnameValue = personalInfoData.getSurname();
                String DateValue = personalInfoData.getBirthDate();
                String ProfessorValue = studentInfoData.getProfessorName();
                String SubjectValue = studentInfoData.getSubject();
                String YearValue = String.valueOf(studentInfoData.getAcademicYear());
                String SubjectHValue = String.valueOf(studentInfoData.getClassesHours());
                String PracticeHValue = String.valueOf(studentInfoData.getPracticeHours());
                String imageBase64 = String.valueOf(personalInfoData.getImageBase64());

                ViewName.setText(getString(R.string.ime) + ": " + NameValue);
                ViewSurname.setText(getString(R.string.prezime) + ": " + SurnameValue);
                ViewDate.setText(getString(R.string.datum_rodjenja) + ": " + DateValue);
                ViewSubject.setText(getString(R.string.predmet) + ": " +  SubjectValue);
                ViewProfessor.setText(getString(R.string.profesor) + ": " + ProfessorValue);
                ViewYear.setText(getString(R.string.godina) + ": " + YearValue);
                ViewClasses.setText(getString(R.string.predavanja) + ": " + SubjectHValue);
                ViewPractice.setText(getString(R.string.vjezbe) + ": " + PracticeHValue);
                PreviewImage.setImageBitmap(ConvertImageToBitmap(imageBase64));
            }
        }
        else
        {
            ViewName.setText(getString(R.string.ime));
            ViewSurname.setText(getString(R.string.prezime));
            ViewDate.setText(getString(R.string.datum_rodjenja));
            ViewSubject.setText(getString(R.string.predmet));
            ViewProfessor.setText(getString(R.string.profesor));
            ViewYear.setText(getString(R.string.godina));
            ViewClasses.setText(getString(R.string.predavanja));
            ViewPractice.setText(getString(R.string.vjezbe));
            PreviewImage.setImageBitmap(null);
        }
    }

    public Bitmap ConvertImageToBitmap(String sImage)
    {
        if(sImage != null)
        {
            byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return image;
        }
        else
        {
            return null;
        }
    }

}