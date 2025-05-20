package com.example.isproyect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.isproyect.R;
import com.example.isproyect.databinding.ActivityLoginBinding;
import com.example.isproyect.databinding.ActivityMainBinding;
import com.example.isproyect.databinding.ActivitySignUpBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /*
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

        Objects.requireNonNull(binding.eventoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
                String id = sp.getString("user", "");
                Intent intent;
                if (id.isEmpty()) {
                    intent = new Intent(MainActivity.this, Login.class);
                }else{
                    intent = new Intent(MainActivity.this, Evento.class);
                }
                startActivity(intent);
                finish();
            }
        });

        Objects.requireNonNull(binding.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("user", "");
                ed.apply();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


    }
}