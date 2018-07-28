package com.luzdelsur.swapi;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.luzdelsur.swapi.entidades.PageHelper;
import com.luzdelsur.swapi.presentacion.PersonajeListAdapter;
import com.luzdelsur.swapi.presentacion.PersonajesListViewModel;

public class MainActivity extends AppCompatActivity {

    private static final int PERIOD_MS = 5000;

    private RecyclerView personajesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LifecycleRegistry registry = new LifecycleRegistry(this);
    private PersonajesListViewModel viewModel;
    private ProgressBar cargaPersonajes;
    private PageHelper actual;
    private Button btnAnterior;
    private Button btnSiguiente;

    @Override
    public Lifecycle getLifecycle() {
        return registry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Programando Job
        // MostrarHoraJobService.refreshSchedule(getApplicationContext());

        viewModel = createViewModel();

        viewModel.obtenerPersonajes(1);

        cargaPersonajes = findViewById(R.id.cargaPersonajes);

        btnAnterior = findViewById(R.id.btnPrev);

        btnAnterior.setOnClickListener( e -> {
            if(actual.getAnterior() != null) {
                viewModel.obtenerPersonajes(actual.getAnterior());
            }
        });

        btnSiguiente = findViewById(R.id.btnNext);

        btnSiguiente.setOnClickListener( e -> {
            if(actual.getSiguiente() != null) {
                viewModel.obtenerPersonajes(actual.getSiguiente());
            }
        });

        personajesRecyclerView = findViewById(R.id.personajesRecyclerView);

        layoutManager = new LinearLayoutManager(this);

        attachObserver();

    }

    private void attachObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            cargaPersonajes.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.getApiError().observe(this, apiError -> {
            Snackbar.make(personajesRecyclerView, apiError.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        });

        viewModel.getPersonajes().observe(this, personajes -> {
            personajesRecyclerView.setLayoutManager(layoutManager);
            personajesRecyclerView.setAdapter(new PersonajeListAdapter(personajes));
        });

        viewModel.getPageHelper().observe(this, pageHelper -> {
            btnAnterior.setEnabled(pageHelper.getAnterior() != null);
            btnSiguiente.setEnabled(pageHelper.getSiguiente() != null);
            actual = pageHelper;
        });
    }

    private PersonajesListViewModel createViewModel() {
        return ViewModelProviders.of(this).get(PersonajesListViewModel.class);
    }

}
