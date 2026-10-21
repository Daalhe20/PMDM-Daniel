package com.example.listadaniel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadaniel.Pokemon;

import java.util.ArrayList;
public class AdaptadorPokemon
        extends RecyclerView.Adapter<AdaptadorPokemon.ViewHolder> {
    private final ArrayList<Pokemon> pokemons;
    public AdaptadorPokemon(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_pokemon, parent, false);
        return new ViewHolder(fila);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.tvTitulo.setText(pokemon.getTitulo());
        holder.tvSubtitulo.setText(pokemon.getAnio());
        holder.ivImagen.setImageResource(pokemon.getImagen());
    }
    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvSubtitulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo);
        }
    }
}
