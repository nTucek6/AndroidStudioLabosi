package com.example.lv4.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lv4.FragmentDataClass.StudentInfoData;
import com.example.lv4.R;
import com.google.android.material.textfield.TextInputEditText;


public class StudentInfoFragment extends Fragment {

    private View rootView;

    public TextInputEditText TextBoxInsertProfessor;
    public TextInputEditText TextBoxInsertSubject;
    public TextInputEditText TextBoxInsertYear;
    public TextInputEditText TextBoxInsertClasses;
    public TextInputEditText TextBoxInsertPractice;

    private StudentInfoData studentInfoData;


    public StudentInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_student_info, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextBoxInsertProfessor = view.findViewById(R.id.ProfesorId);
        TextBoxInsertSubject = view.findViewById(R.id.PredmetId);
        TextBoxInsertYear = view.findViewById(R.id.GodinaId);
        TextBoxInsertClasses = view.findViewById(R.id.PredavanjaSatiId);
        TextBoxInsertPractice = view.findViewById(R.id.VjezbeSatiId);
    }


    public StudentInfoData SendData()
    {
        try
        {
            String professor = TextBoxInsertProfessor.getText().toString();
            String subject = TextBoxInsertSubject.getText().toString();
            int year = Integer.parseInt(TextBoxInsertYear.getText().toString());
            int classes = Integer.parseInt(TextBoxInsertClasses.getText().toString());
            int practice = Integer.parseInt(TextBoxInsertPractice.getText().toString());

            studentInfoData = new StudentInfoData(professor,subject,year,classes,practice);

            if(studentInfoData.CheckEmpty())
            {
                return studentInfoData;
            }
            else
            {
                return null;
            }

        }catch (Exception e)
        {
            return null;
        }

    }

}