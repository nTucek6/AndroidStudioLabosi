package com.example.lv4.Retrofit;

import com.google.gson.annotations.SerializedName;

public class SubjectGet {

    @SerializedName("code")
    private int Code;

    @SerializedName("name")
    private String Subject;

    @SerializedName("url")
    private String URL;

    public int getCode() {
        return Code;
    }

    public String getURL() {
        return URL;
    }

    public String getSubject() {
        return Subject;
    }


}
