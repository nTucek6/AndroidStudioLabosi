package com.example.lv4.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClass {

    @SerializedName("courses")
    private List<SubjectGet> Subjects;

   // @SerializedName("metadata")
  //  private MetaData metaData;


    public void setSubjects(List<SubjectGet> subjects) {
        Subjects = subjects;
    }

    public List<SubjectGet> getSubjects() {
        return Subjects;
    }

   /* public MetaData getMetaData() {
        return metaData;
    } */
}
