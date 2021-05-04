package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroesViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> heroList;  // the info we want to reload

    public LiveData<List<Hero>> getHeroList() {
        if (heroList == null) {
            heroList = new MutableLiveData<>();
            loadHeroes();
        }

        return heroList;
    }

    private void loadHeroes() {
        Api api = ApiUtil.getRetrofitApi();
        Call<List<Hero>> call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                heroList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }
}
