package com.luzdelsur.swapi.presentacion;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luzdelsur.swapi.R;
import com.luzdelsur.swapi.entidades.Personaje;

import java.util.ArrayList;
import java.util.List;

public class PersonajeListAdapter extends RecyclerView.Adapter<PersonajeListAdapter.ViewHolder> {

    private List<Personaje> dataModel;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView peso;
        public TextView talla;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            talla = itemView.findViewById(R.id.talla);
            peso = itemView.findViewById(R.id.peso);
        }
    }

    public PersonajeListAdapter(List<Personaje> dataModel) {
        this.dataModel = dataModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(dataModel.get(position).getNombre());
        holder.talla.setText(dataModel.get(position).getTalla());
        holder.peso.setText(dataModel.get(position).getPeso());
    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }
}
