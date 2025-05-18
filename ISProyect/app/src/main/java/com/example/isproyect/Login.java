package com.example.isproyect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.isproyect.databinding.ActivityLoginBinding;

import java.util.Objects;

import SQL.Usuario;
import SQL.UsuarioDBconexion;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    UsuarioDBconexion usuarioDB;

    public class Session {

        private SharedPreferences prefs;

        public Session(Context ctx) {
            prefs = getSharedPreferences( getPackageName() + "_preferences", Context.MODE_PRIVATE);
        }

        public void setusername(String username) {
            prefs.edit().putString("username", username).apply();
        }

        public String getusername() {
            String username1;
            username1 = prefs.getString("username","");
            return username1;
        }
    }


    @SuppressLint({"MissingInflatedId", "ApplySharedPref"})
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

        Objects.requireNonNull(binding.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.loginUsuario.getText().toString();
                String pass = binding.loginPassword.getText().toString();

                if (user.isEmpty()||pass.isEmpty())
                    Toast.makeText(Login.this,"Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                else if(usuarioDB.checkUserPass(user, pass))
                {
                    SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("user", user);
                    ed.apply();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(Login.this,"Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Objects.requireNonNull(binding.signupRedirectText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, sign_up.class);
                startActivity(intent);
                finish();
            }
        });

        Objects.requireNonNull(binding.debugButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String user = binding.loginUsuario.getText().toString();
                SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("user", user);
                ed.apply();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        });





    }
}

