package com.owais.tmdbexample.service;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit retrofit=null;
    public static String BASE_URL="https://api.themoviedb.org/3/";

    public static MovieDataService getService(){
        if(retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieDataService.class);
    }
}
