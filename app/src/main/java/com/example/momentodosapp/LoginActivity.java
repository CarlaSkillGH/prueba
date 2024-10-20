package com.example.momentodosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Obtener referencias de los elementos del layout
        Button loginButton = findViewById(R.id.loginButton);

        // Escuchar el clic en el botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a ProductActivity
                Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
