package com.example.lv4.Class;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManipulator {

    public static String LoadJson(Context context, String FILE_NAME)
    {
        String response = null;
        try {
            File file = new File(context.getFilesDir(),FILE_NAME);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();// This response will have Json Format String
            response = stringBuilder.toString();
        }
        catch (IOException e)
        {
        }
        return response;
    }

    public static List<SaveSummary> jsonStringToObjectSummary(String sJson) {
        JSONArray oArray = null;
        List<SaveSummary> summaryList = new ArrayList<>();
        try {
            oArray = new JSONArray(sJson);
            int n = oArray.length();
            for (int i = 0; i < n; i++) {
                JSONObject summary = oArray.getJSONObject(i);
                String name = summary.getString("Name");
                String surname = summary.getString("Surname");
                String ImageBase64 = summary.getString("ImageBase64");
                String subject = summary.getString("Subject");
                summaryList.add(new SaveSummary(name,surname,subject,ImageBase64));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  summaryList;
    }


    //Class
    public static void WriteToJson(Context context,String sFileName , String json)
    {
        try {
            File fileDirectory = context.getFilesDir();
            if(!fileDirectory.exists())
            {
                fileDirectory.mkdirs();
            }
            File file = new File(fileDirectory,sFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }





}
