package com.luzdelsur.swapi.repositorio;

import com.luzdelsur.swapi.entidades.Personaje;
import com.luzdelsur.swapi.entidades.PersonajesList;

import java.util.List;
import java.util.function.Consumer;

public interface PersonajesRepository {

    void obtenerListaPersonajes(Consumer<PersonajesList> success, Consumer<Throwable> failure, int page);

}
