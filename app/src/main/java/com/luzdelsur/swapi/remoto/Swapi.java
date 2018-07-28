package com.luzdelsur.swapi.remoto;


import com.luzdelsur.swapi.entidades.PersonajesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Swapi {

    @GET("people/")
    Call<PersonajesList> obtenerListaPersonajes(@Query("page") int page);

}
