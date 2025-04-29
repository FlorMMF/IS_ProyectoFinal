package com.example.sqliteprueba3;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import SQL.Paciente;
import SQL.pacientesDBconexion;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            pacientesDBconexion dbConexion = new pacientesDBconexion(this);

            Paciente nuevoPaciente = new Paciente("Juan", "Pérez", "1234567890");
            long resultado = dbConexion.GuardarPaciente(nuevoPaciente);

            if (resultado != -1) {
                Log.d("DB", "Paciente guardado con éxito con ID: " + resultado);
            } else {
                Log.e("DB", "Error al guardar el paciente");
            }

            dbConexion.leerPacientes();
        } catch (Exception e) {
            Log.e("DB", "Error general en MainActivity", e);
        }
    }


}
