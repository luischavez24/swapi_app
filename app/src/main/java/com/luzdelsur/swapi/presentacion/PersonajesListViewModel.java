package com.luzdelsur.swapi.presentacion;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.luzdelsur.swapi.entidades.PageHelper;
import com.luzdelsur.swapi.entidades.Personaje;
import com.luzdelsur.swapi.remoto.Apis;
import com.luzdelsur.swapi.repositorio.PersonajesRepository;
import com.luzdelsur.swapi.repositorio.impl.PersonajesRepositoryImpl;

import java.util.List;

public class PersonajesListViewModel extends ViewModel {

    private PersonajesRepository repository;

    private MutableLiveData<Boolean> isLoading;

    private MutableLiveData<Throwable> apiError;

    private MutableLiveData<PageHelper> pageHelper;

    private MutableLiveData<List<Personaje>> personajes;

    public PersonajesListViewModel() {
        super();
        repository = new PersonajesRepositoryImpl(Apis.getSwapi());
        isLoading = new MutableLiveData<>();
        apiError = new MutableLiveData<>();
        personajes = new MutableLiveData<>();
        pageHelper = new MutableLiveData<>();
    }

    public void obtenerPersonajes(int page) {
        isLoading.setValue(true);
        repository.obtenerListaPersonajes(personajeList -> {

            Integer anterior = pageNumber(personajeList.getAnterior());

            Integer siguiente = pageNumber(personajeList.getSiguiente());

            pageHelper.setValue(new PageHelper(anterior,siguiente, page));

            personajes.setValue(personajeList.getPersonajeList());

            isLoading.setValue(false);

        }, throwable -> {

            apiError.setValue(throwable);

            isLoading.setValue(false);

        }, page);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    public MutableLiveData<Throwable> getApiError() {
        return apiError;
    }

    public void setApiError(MutableLiveData<Throwable> apiError) {
        this.apiError = apiError;
    }

    public MutableLiveData<List<Personaje>> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(MutableLiveData<List<Personaje>> personajes) {
        this.personajes = personajes;
    }

    public MutableLiveData<PageHelper> getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(MutableLiveData<PageHelper> pageHelper) {
        this.pageHelper = pageHelper;
    }

    private Integer pageNumber(String pageString) {
        Integer pageNumber = null;
        if(pageString != null) {
            String newString = pageString.replaceAll("https:\\/\\/swapi\\.co\\/api\\/people\\/\\?page=", "");
            pageNumber = Integer.parseInt(newString);
        }
        return pageNumber;

    }
}
