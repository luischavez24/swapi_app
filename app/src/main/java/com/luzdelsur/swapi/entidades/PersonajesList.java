package com.luzdelsur.swapi.entidades;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonajesList {

    @SerializedName("results")
    private List<Personaje> personajeList;

    @SerializedName("next")
    private String siguiente;

    @SerializedName("previous")
    private String anterior;

    public PersonajesList( ) { }

    public List<Personaje> getPersonajeList() {
        return personajeList;
    }

    public void setPersonajeList(List<Personaje> personajeList) {
        this.personajeList = personajeList;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        this.anterior = anterior;
    }
}
