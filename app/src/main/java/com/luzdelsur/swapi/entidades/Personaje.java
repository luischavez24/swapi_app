package com.luzdelsur.swapi.entidades;

import com.google.gson.annotations.SerializedName;

public class Personaje {

    @SerializedName("name")
    private String nombre;

    @SerializedName("height")
    private String talla;

    @SerializedName("mass")
    private String peso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
