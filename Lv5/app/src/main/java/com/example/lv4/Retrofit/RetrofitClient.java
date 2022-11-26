package com.example.lv4.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //singleton instance of retrofit


    private static final String BASE_URL = "https://data.uio.no/studies/v1/";
    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
