package com.example.pinguiapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    Button btnLog;
    TextInputEditText Usr;
    TextInputEditText pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // botones
        btnLog = findViewById(R.id.btnIniciarSesion);
        Usr = findViewById(R.id.txtCorreo);
        pass = findViewById(R.id.txtContra);

        // Habilita el modo Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_login), (v, insets) -> {
            // Obtiene los insets de las barras del sistema (status bar, navigation bar)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Aplica los insets como padding a la vista raíz
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el TextView y establece el OnClickListener
        TextView createAccountTextView = findViewById(R.id.createAccountTextView);
        createAccountTextView.setOnClickListener(v -> {
            // Inicia la actividad RegistroActivity
            Intent intent = new Intent(Login.this, RegistroActivity.class);
            startActivity(intent);
        });

        // Configura el botón de inicio de sesión
        btnLog.setOnClickListener(view -> {
            String Usrname = Usr.getText().toString();
            String PassInt = pass.getText().toString();

            if (Usrname.isEmpty() || PassInt.isEmpty()) {
                Toast.makeText(Login.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                new LoginTask().execute(Usrname, PassInt);
            }
        });
    }

    // Clase AsyncTask para manejar la autenticación en segundo plano
    @SuppressLint("StaticFieldLeak")
    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            // Conexión a la base de datos y autenticación
            Conexion conexion = new Conexion();
            conexion.conectar();
            boolean loginSuccess = conexion.loguearUsuario(username, password);
            conexion.desconectar();
            return loginSuccess;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Aquí puedes redirigir a la actividad principal
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
