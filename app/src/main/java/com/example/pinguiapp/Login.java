package com.example.pinguiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Habilita el modo Edge-to-Edge
        EdgeToEdge.enable(this);

        // Establece un listener para aplicar los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_login), (v, insets) -> {
            // Obtiene los insets de las barras del sistema (status bar, navigation bar)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Aplica los insets como padding a la vista raíz
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Retorna los insets para que el sistema siga aplicándolos
            return insets;
        });

        // Encuentra el TextView y establece el OnClickListener
        TextView createAccountTextView = findViewById(R.id.createAccountTextView);
        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad RegistroActivity
                Intent intent = new Intent(Login.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
