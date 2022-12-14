package com.example.lv4;

import static com.example.lv4.Class.JsonManipulator.LoadJson;
import static com.example.lv4.Class.JsonManipulator.WriteToJson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lv4.FragmentDataClass.PersonalInfoData;
import com.example.lv4.FragmentDataClass.StudentInfoData;
import com.example.lv4.Fragments.PersonalInfoFragment;
import com.example.lv4.Fragments.StudentInfoFragment;
import com.example.lv4.Fragments.SummaryFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateNewRecordActivity extends AppCompatActivity {

        private final static String  FILE_SUMMARY = "SummaryInfo.json";

        private ViewPagerAdapter viewPagerAdapter;
        private ViewPager viewPager;
        private TabLayout tabLayout;

        private PersonalInfoFragment personalInfoFragment;
        private StudentInfoFragment studentInfoFragment;
        private SummaryFragment summaryFragment;

    public void onBackPressed()
    {
       CreateNewRecordActivity.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //add the fragments
        viewPagerAdapter.add(new PersonalInfoFragment(), getString(R.string.personalInfoTab));
        viewPagerAdapter.add(new StudentInfoFragment(),getString(R.string.studentInfoTab));
        viewPagerAdapter.add(new SummaryFragment(),getString(R.string.summaryTab));

        //Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        //Display fragments title into tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

       viewPager.addOnPageChangeListener(new  ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if(position == 2)
            {
               personalInfoFragment = (PersonalInfoFragment) viewPagerAdapter.getItem(0);
               studentInfoFragment = (StudentInfoFragment) viewPagerAdapter.getItem(1);

               PersonalInfoData personalInfoData = personalInfoFragment.SendData();
               StudentInfoData studentInfoData = studentInfoFragment.SendData();

               if(personalInfoData != null && studentInfoData != null)
               {
                   summaryFragment = (SummaryFragment) viewPagerAdapter.getItem(2);
                   summaryFragment.SetSummaryData(personalInfoData,studentInfoData);

                   summaryFragment.btnRegister.setEnabled(true);
                   summaryFragment.btnRegister.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           ParseListsData(personalInfoData,studentInfoData);
                           startActivity(new Intent(CreateNewRecordActivity.this,MainActivity.class));
                           CreateNewRecordActivity.this.finish();
                       }
                   });
               }
               else
               {
                   Toast.makeText(CreateNewRecordActivity.this, getString(R.string.EmptyField),Toast.LENGTH_SHORT).show();
                   if(summaryFragment != null)
                   {
                       summaryFragment.SetSummaryData(null,null);
                       summaryFragment.btnRegister.setEnabled(false);
                   }
               }
            }
        }

        @Override
        public void onPageSelected(int position) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

    }
    private void DisableTab(TabLayout TabLayout,int position,boolean toggle)
    {
        ((ViewGroup)TabLayout.getChildAt(0)).getChildAt(position).setEnabled(toggle);
        //((ViewGroup)TabLayout.getChildAt(0)).getChildAt(position).s(toggle);
    }

    private void ParseListsData(PersonalInfoData personalInfoData,StudentInfoData studentInfoData)
    {
        String oldJson = LoadJson(CreateNewRecordActivity.this,FILE_SUMMARY);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonarray = null;
        try{
            if(oldJson != null)
            {
                jsonarray = new JSONArray(oldJson);
            }
            else
            {
                jsonarray = new JSONArray();
            }

            jsonObject.put("Name", personalInfoData.getName());
            jsonObject.put("Surname", personalInfoData.getSurname());
            jsonObject.put("ImageBase64",personalInfoData.getImageBase64());
            jsonObject.put("Subject", studentInfoData.getSubject());

            jsonarray.put(jsonObject);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        String json = jsonarray.toString();
        WriteToJson(CreateNewRecordActivity.this,FILE_SUMMARY,json);
    }


}