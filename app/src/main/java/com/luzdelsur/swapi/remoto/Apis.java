package com.luzdelsur.swapi.remoto;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apis {
    private static final String SWAPI_URL = "https://swapi.co/api/";

    public static Swapi getSwapi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SWAPI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Swapi.class);
    }
}
