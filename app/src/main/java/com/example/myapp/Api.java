package com.example.myapp;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("marvel")
    Call<List<Hero>> getHeroes();

    @GET("{which}")
    Call<List<Hero>> getHeroes(@Path("which") String path);

    // @GET("/maps/api/geocode/json?")
    // Call<JsonObject> getLocationInfo(@Query("address") String zipCode, @Query("sensors") boolean sensors);
    // &address=90210&sensors=false
}
