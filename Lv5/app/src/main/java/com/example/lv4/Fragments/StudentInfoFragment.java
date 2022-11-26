package com.example.lv4.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lv4.FragmentDataClass.StudentInfoData;
import com.example.lv4.R;
import com.example.lv4.Retrofit.ResponseClass;
import com.example.lv4.Retrofit.RetrofitClient;
import com.example.lv4.Retrofit.SubjectGet;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentInfoFragment extends Fragment {

    private View rootView;

    public TextInputEditText TextBoxInsertProfessor;
   // public TextInputEditText TextBoxInsertSubject;
    public Spinner SubjectSpinner;
    public TextInputEditText TextBoxInsertYear;
    public TextInputEditText TextBoxInsertClasses;
    public TextInputEditText TextBoxInsertPractice;
    public ProgressBar progressBar;

    private StudentInfoData studentInfoData;


    List<String> subjectList= new ArrayList<>();

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
        SubjectSpinner = view.findViewById(R.id.PredmetId);
        TextBoxInsertYear = view.findViewById(R.id.GodinaId);
        TextBoxInsertClasses = view.findViewById(R.id.PredavanjaSatiId);
        TextBoxInsertPractice = view.findViewById(R.id.VjezbeSatiId);
        progressBar = view.findViewById(R.id.progressBar);


        fetchPosts();

    }


    private void fetchPosts() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getSubjects().enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<ResponseClass> getData = new ArrayList<>();

                    subjectList.add("Odaberite predmet:");
                    getData.add(response.body());
                    for (ResponseClass s: getData)
                    {
                        for (SubjectGet subject: s.getSubjects())
                        {
                            subjectList.add(subject.getSubject());
                        }
                    }

                    ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, subjectList);
                    adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    SubjectSpinner.setAdapter(adapterSpinner);
                    //adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                //progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("",t.getMessage());

            }
        });
    }


    public StudentInfoData SendData()
    {
        try
        {
            String professor = TextBoxInsertProfessor.getText().toString();
            String subject = SubjectSpinner.getSelectedItem().toString();
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