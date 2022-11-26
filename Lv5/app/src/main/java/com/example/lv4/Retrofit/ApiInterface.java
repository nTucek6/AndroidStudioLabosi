package com.example.lv4.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("courses?limit=5")
    Call<ResponseClass> getSubjects();

}
