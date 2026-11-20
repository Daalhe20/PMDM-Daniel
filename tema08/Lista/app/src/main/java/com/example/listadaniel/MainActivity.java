package com.example.listadaniel;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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

        ArrayList<Pokemon> pokemons = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(leerRaw(R.raw.datos));
            for (int i = 0; i < array.length(); i++) {
                JSONObject objeto = array.getJSONObject(i);
                pokemons.add(new Pokemon(
                        objeto.getString("titulo"),
                        objeto.getString("anio"),
                        objeto.getString("imagen")));
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error al leer datos.json", e);
            Toast.makeText(this, R.string.error_json, Toast.LENGTH_LONG).show();
        }

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorPokemon(pokemons));
    }

    private String leerRaw(int idRecurso) throws IOException {
        try (InputStream entrada = getResources().openRawResource(idRecurso);
             BufferedReader lector = new BufferedReader(
                     new InputStreamReader(entrada, StandardCharsets.UTF_8))) {
            StringBuilder texto = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                texto.append(linea).append('\n');
            }
            return texto.toString();
        }
    }

}