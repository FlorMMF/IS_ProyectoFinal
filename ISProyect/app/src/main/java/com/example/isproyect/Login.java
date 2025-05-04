package com.example.isproyect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.isproyect.databinding.ActivityLoginBinding;

import SQL.Usuario;
import SQL.UsuarioDBconexion;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    UsuarioDBconexion usuarioDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        usuarioDB = new UsuarioDBconexion(this);

/*
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
*/

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.loginUsuario.getText().toString();
                String pass = binding.loginPassword.getText().toString();

                if (user.isEmpty()||pass.isEmpty())
                    Toast.makeText(Login.this,"Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                else if(usuarioDB.checkUserPass(user, pass))
                {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(Login.this,"Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, sign_up.class);
                startActivity(intent);
            }
        });
    }
}