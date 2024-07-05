package com.example.pinguiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {

    Button btnLog;
    TextInputEditText Usr;
    TextInputEditText pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Botones
        btnLog = findViewById(R.id.btnIniciarSesion);
        Usr = findViewById(R.id.txtCorreo);
        pass = findViewById(R.id.txtContra);

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
        createAccountTextView.setOnClickListener(v -> {
            // Inicia la actividad RegistroActivity
            Intent intent = new Intent(Login.this, RegistroActivity.class);
            startActivity(intent);
        });

        btnLog.setOnClickListener(view -> {
            String usuario = Usr.getText().toString();
            String contraseña = pass.getText().toString();
            new Conexion(this).execute(usuario, contraseña);
        });
    }

    // Clase interna para manejar la conexión a la base de datos
    private static class Conexion extends AsyncTask<String, Void, Boolean> {
        // Atributos de clase
        private final String dirIp = "bhy07o9l8zzptvw9aad1-mysql.services.clever-cloud.com";
        private final String usr = "urhzabcaylbcx2t7";
        private final String pass = "D9ln66kQMBArEt4wMcU8";
        private final String bd = "bhy07o9l8zzptvw9aad1";
        private final String pto = "3306";
        private final Login loginActivity;
        private Connection con = null;

        public Conexion(Login loginActivity) {
            this.loginActivity = loginActivity;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String usuario = params[0];
            String contraseña = params[1];
            try {
                // Cargar el DRIVER
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Hacer la cadena de conexión
                con = DriverManager.getConnection("jdbc:mysql://" + dirIp + ":" + pto + "/" + bd, usr, pass);
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, usuario);
                statement.setString(2, contraseña);
                ResultSet rs = statement.executeQuery();
                return rs.next(); // true si encuentra el usuario, false de lo contrario
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean loginExitoso) {
            if (loginExitoso) {
                Toast.makeText(loginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Aquí puedes iniciar una nueva actividad si el login es exitoso
                // Intent intent = new Intent(loginActivity, NuevaActividad.class);
                // loginActivity.startActivity(intent);
            } else {
                Toast.makeText(loginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


