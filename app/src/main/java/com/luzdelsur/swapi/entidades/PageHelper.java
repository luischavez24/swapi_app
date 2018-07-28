package com.luzdelsur.swapi.entidades;

public class PageHelper {

    private Integer anterior;

    private Integer siguiente;

    private Integer actual;

    public PageHelper(Integer anterior, Integer siguiente, Integer actual) {
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.actual = actual;
    }

    public Integer getAnterior() {
        return anterior;
    }

    public void setAnterior(Integer anterior) {
        this.anterior = anterior;
    }

    public Integer getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Integer siguiente) {
        this.siguiente = siguiente;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }
}
