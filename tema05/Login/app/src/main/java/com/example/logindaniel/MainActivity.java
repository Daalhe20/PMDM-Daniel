package com.example.logindaniel;

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

public class MainActivity extends AppCompatActivity {
    private static final String CORREO_VALIDO = "dani@correo.com";
    private static final String CLAVE_VALIDA = "1234";

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

        btnContinuar.setOnClickListener(v -> {
            String correo = editTxtCorreo.getText().toString().trim();
            String clave = editTxtContra.getText().toString();
            if (correo.equals(CORREO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
                if (switchRecordar.isChecked()) {
                    txtMensaje.setText("Usuario y contraseña correctos.     " +
                            "       Almacenados para siguientes accesos");
                    txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.green));
                } else {
                    txtMensaje.setText("Usuario y contraseña correctos");
                    txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.green));
                }
            } else {
                txtMensaje.setText("Usuario y/o contraseña incorrectos");
                txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.red));
            }
        });
    }
}