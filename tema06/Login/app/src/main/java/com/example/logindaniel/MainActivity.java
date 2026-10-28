package com.example.logindaniel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.materialswitch.MaterialSwitch;
import android.widget.Toast;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private static final String CORREO_VALIDO = "dani@correo.com";
    private static final String CLAVE_VALIDA = "1234";
    private int intentos = 0;

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

        //enlazamos con activity_main.xml
        EditText editTxtCorreo = findViewById(R.id.editTxtCorreo);
        EditText editTxtContra = findViewById(R.id.editTxtContra);
        MaterialSwitch switchRecordar = findViewById(R.id.switch1);
        Button btnContinuar = findViewById(R.id.btnContinuar);
        TextView txtMensaje = findViewById(R.id.txtMensaje);

        SharedPreferences prefs = getSharedPreferences("datos_app", MODE_PRIVATE);
        String correoGuardado = prefs.getString("usuario", "");

        if (!correoGuardado.isEmpty()) {
            editTxtCorreo.setText(correoGuardado);
            switchRecordar.setChecked(true);
        }

        btnContinuar.setOnClickListener(v -> {
            String correo = editTxtCorreo.getText().toString().trim();
            String contra = editTxtContra.getText().toString();

            if (correo.isEmpty()) {
                editTxtCorreo.setError(getString(R.string.faltaCorreo));
                return;
            } else if (contra.isEmpty()) {
                editTxtContra.setError(getString(R.string.faltaContra));
                return;
            }

            if (correo.equals(CORREO_VALIDO) && contra.equals(CLAVE_VALIDA)) {
                intentos = 0;

                if (switchRecordar.isChecked()){
                    prefs.edit().putString("usuario", correo).apply();
                } else {
                    prefs.edit().remove("usuario").apply();
                }

                Intent intent = new Intent(this, Second_Activity.class);
                intent.putExtra(Second_Activity.EXTRA_CORREO, correo);
                startActivity(intent);

                finish();
            } else {
                txtMensaje.setText("Usuario y/o contraseña incorrectos");
                txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.red));
                intentos ++;
                if (intentos >= 3) {
                    btnContinuar.setEnabled(false);
                    txtMensaje.setText("Demasiados intentos");
                    Toast.makeText(this, R.string.botonBloqueado, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}