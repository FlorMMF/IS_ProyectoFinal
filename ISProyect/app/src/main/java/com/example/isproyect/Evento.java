package com.example.isproyect;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.isproyect.databinding.ActivityEventoBinding;

import java.util.ArrayList;
import java.util.Collections;

public class Evento extends AppCompatActivity {

    ActivityEventoBinding binding;
    TextView textView;
    boolean[] selectedManif;
    ArrayList<Integer> manifList = new ArrayList<>();
    String[] manifArray = {"1.Perdida de la conciencia", "2.Sensación de desorentación", "3.Trastornos visuales",
            "4.Trastornos auditivos", "5.Trastorno sensorial del cuerpo", "6.Trastornos olfatorios","7.Movimiento en manos o pies",
            "8.Ausencia de moviento", "9.Relajación de esfínter Vesical","10.Relajación de esfínter Anal", "11.Rigidez de manos y/o pies",
            "12.Relajación de musculos","13.Sacudidas de todo o parte del cuerpo"};
    String[] manifKey={"MF_01","MF_02","MF_03","MF_04","MF_05","MF_06","MF_07","MF_08","MF_09","MF_10","MF_11",
            "MF_12","MF_13"};

    private String fecha;
    private String hora;
    private String manif;
    private String farm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_evento);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        selectedManif = new boolean[manifArray.length];
        binding.eventoManif.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Evento.this);

                // set title
                builder.setTitle("Elija manifestaciones");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(manifArray, selectedManif, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            manifList.add(i);
                            // Sort array list
                            Collections.sort(manifList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            manifList.remove(Integer.valueOf(i));
                        }
                    }
                });
                Button addFarmacoButton = findViewById(R.id.add_farmaco);
                View cbzView = findViewById(R.id.CBZ);
                addFarmacoButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        cbzView.setVisibility(View.VISIBLE);
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < manifList.size(); j++) {
                            // concat array value
                            stringBuilder.append(manifKey[manifList.get(j)]);
                            // check condition
                            if (j != manifList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        manif = stringBuilder.toString();
                        //binding.eventoManif.setText(manif);
                        Toast.makeText(Evento.this, manif, Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Borrar Todo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedManif.length; j++) {
                            // remove all selection
                            selectedManif[j] = false;
                            // clear language list
                            manifList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();

            }

        });
    }

    private void checkManif(ViewGroup viewById){
        if (binding.eventoConciencia.isChecked()) manif = "Perdida de la conciencia";
        if (binding.eventoDesorientado.isChecked()) manif = "Sensación de desorentación";
        if (binding.eventoVisibilidad.isChecked()) manif = "Trastornos visuales";
        if (binding.eventoAudivilidad.isChecked()) manif = "Trastornos auditivos";
        if (binding.eventoSensorial.isChecked()) manif = "Trastorno sensorial del cuerpo";
        if (binding.eventoOlfato.isChecked()) manif = "Trastornos olfatorios";
        if (binding.eventoMovimiento.isChecked()) manif = "Movimiento en manos o pies";
        if (binding.eventoNOmovimiento.isChecked()) manif = "Ausencia de moviento";
        if (binding.eventoRelajVesicula.isChecked()) manif = "Relajación de esfínter Vesical";
        if (binding.eventoRelajAnal.isChecked()) manif = "Relajación de esfínter Anal";
        if (binding.eventoRigidez.isChecked()) manif = "Rigidez de manos y/o pies";
        if (binding.eventoRelajMusculos.isChecked()) manif = "Relajación de musculos";
        if (binding.eventoSacudidas.isChecked()) manif = "Sacudidas de todo o parte del cuerpo";
    }






}