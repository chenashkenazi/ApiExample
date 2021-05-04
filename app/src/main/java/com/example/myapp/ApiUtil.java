package com.example.myapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {

    static String BASE_URL = "https://simplifiedcoding.net/demos/";

    public static Api getRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        return api;
    }
}
