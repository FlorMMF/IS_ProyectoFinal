package com.example.isproyect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.isproyect.databinding.ActivitySignUpBinding;

import java.util.Calendar;

import SQL.Usuario;
import SQL.UsuarioDBconexion;

public class sign_up extends AppCompatActivity {

ActivitySignUpBinding binding;
UsuarioDBconexion UsuarioDB;

private String date;



    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
/*
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_sign_up), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
*/


        UsuarioDB = new UsuarioDBconexion( this);


        binding.signupButton.setOnClickListener(v -> {
            String user = binding.signupUser.getText().toString();
            String nombre = binding.signupNombre.getText().toString();
            String apellido = binding.signupApellido.getText().toString();
            String pass = binding.signupPassword.getText().toString();
            String pass2 = binding.signupPassword2.getText().toString();

            Usuario nuevo = new Usuario(user, pass);

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(sign_up.this, "Necesita llenar campos obligatorios", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(pass2)) {
                Toast.makeText(sign_up.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else if (UsuarioDB.checkUser(user)) {
                Toast.makeText(sign_up.this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
            } else if (-1 != UsuarioDB.GuardarUsuario(nuevo)){
                Toast.makeText(sign_up.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                clearForm(findViewById(R.id.activity_sign_up));
                Intent intent = new Intent(sign_up.this, Login.class);
                startActivity(intent);
            }else {
                Toast.makeText(sign_up.this,"Error en registro",Toast.LENGTH_SHORT).show();
            }
        });


        binding.cancelButton.setOnClickListener(v -> {
            clearForm(findViewById(R.id.activity_sign_up));
            Intent intent = new Intent(sign_up.this, Login.class);
            startActivity(intent);
        });


        binding.signupNacimiento.setOnClickListener(v-> {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog dialog = new DatePickerDialog(
                        sign_up.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int year, int month, int dayOfMonth) {
                        date =dayOfMonth + "/" + (month + 1) + "/" + year;
                        binding.signupNacimiento.setText(date);
                    }
                } , year, month, day) ;

                dialog.show();

        });
    }

    private void clearForm(ViewGroup viewById) {
        for (int i = 0, count = viewById.getChildCount(); i < count; ++i) {
            View view = viewById.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }



}

