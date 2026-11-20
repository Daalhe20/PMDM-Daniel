package com.example.listadaniel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Los datos: al menos 8 elementos
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Rojo Fuego", "2004", R.drawable.rojo_fuego));
        pokemons.add(new Pokemon("Leyendas Arceus", "2022", R.drawable.arceus));
        pokemons.add(new Pokemon("Blanco 2, Negro 2", "2012", R.drawable.blanco_negro_2));
        pokemons.add(new Pokemon("Platino", "2008", R.drawable.platino));
        pokemons.add(new Pokemon("Cristal", "2000", R.drawable.cristal));
        pokemons.add(new Pokemon("Esmeralda", "2004", R.drawable.esmeralda));
        pokemons.add(new Pokemon("Oro, Plata", "1999", R.drawable.oro_plata));
        pokemons.add(new Pokemon("Rubi, Zafiro", "2002", R.drawable.rubi_zafiro));

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorPokemon(pokemons));
    }
}