package com.example.pinguiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {


    Button  registro;
    TextInputEditText nameUser;
    TextInputEditText  Correo;
    TextInputEditText  password;
    TextInputEditText  confirPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Asegúrate de que este sea el layout correcto

        // Usa el ID correcto del layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_register), (v, insets) -> {
            // Maneja los insets si es necesario
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets.consumeSystemWindowInsets();
        });

        registro = findViewById(R.id.registerButton);
        nameUser = findViewById(R.id.UserTxt);
        Correo = findViewById(R.id.EmailText);
        password = findViewById(R.id.passwordTextInputLayout);
        confirPass = findViewById(R.id.confirmPasswordEditText);










        // Encuentra el TextView y establece el OnClickListener
        TextView loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad LoginActivity
                Intent intent = new Intent(RegistroActivity.this, com.example.pinguiapp.Login.class);
                startActivity(intent);
            }
        });
    }
}
