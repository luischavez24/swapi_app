package com.luzdelsur.swapi.repositorio.impl;

import com.luzdelsur.swapi.entidades.Personaje;
import com.luzdelsur.swapi.entidades.PersonajesList;
import com.luzdelsur.swapi.remoto.Swapi;
import com.luzdelsur.swapi.repositorio.PersonajesRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajesRepositoryImpl implements PersonajesRepository {

    private Swapi apiService;

    public PersonajesRepositoryImpl(Swapi apiService) {
        this.apiService = apiService;
    }

    @Override
    public void obtenerListaPersonajes(Consumer<PersonajesList> success, Consumer<Throwable> failure, int page) {

        apiService.obtenerListaPersonajes(page).enqueue(new Callback<PersonajesList>() {

            @Override
            public void onResponse(Call<PersonajesList> call, Response<PersonajesList> response) {

                Optional.of(response).ifPresent((r) -> {

                    Optional.of(r.body()).ifPresent((b) -> {

                        success.accept(b);

                    });

                });
            }

            @Override
            public void onFailure(Call<PersonajesList> call, Throwable t) {
                failure.accept(t);
            }
        });
    }
}
