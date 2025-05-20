package com.example.isproyect;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.widget.TimePicker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.isproyect.databinding.ActivityEventoBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

import SQL.Evento;
import SQL.UsuarioDBconexion;

public class Activity_Evento extends AppCompatActivity {

    ActivityEventoBinding binding;
    UsuarioDBconexion EventoDB;

    boolean[] selectedManif;
    ArrayList<Integer> manifList = new ArrayList<>();
    String[] manifArray = {"1.Perdida de la conciencia", "2.Sensación de desorentación", "3.Trastornos visuales",
            "4.Trastornos auditivos", "5.Trastorno sensorial del cuerpo", "6.Trastornos olfatorios","7.Movimiento en manos o pies",
            "8.Ausencia de moviento", "9.Relajación de esfínter Vesical","10.Relajación de esfínter Anal", "11.Rigidez de manos y/o pies",
            "12.Relajación de musculos","13.Sacudidas de todo o parte del cuerpo"};
    public static final String[] manifKey={"MF_01","MF_02","MF_03","MF_04","MF_05","MF_06","MF_07","MF_08","MF_09","MF_10","MF_11",
            "MF_12","MF_13"};

    boolean[] selectedFarm;
    ArrayList<Integer> farmList = new ArrayList<>();
    String[] farmArray = {"Carbamazepina", "Clobazam", "Clonazepam","Diazepam", "Etosuximida", "Felbamato","Gabapentina",
            "Levetiracetam", "Lamotrigina","Lorazepam", "Midazolam","Oxcarbazepina","Fenobarbital","Pregabalina",
            "Fenitoína","Primidona","Rufinamida","Tiagabina","Topiramato","Vigabatrina","Ácido valproico","Zonisamida"};
    public static final String[] farmKey={"CBZ","CLB","CZP","DZP","ESM","FBM","GBP","LEV","LTG","LZP","MDZ","OXC","PB","PGB","PHT","PRM",
            "RFM","TGB","TPM","VGB","VPA","ZNS"};

    private String fecha;
    private String horaEvento;
    private String manif;
    private String farm;
    //private String dosis;
    //private String frec;

    private int year;
    private int month;
    private int day;

    private int hora;
    private int minuto;





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

        EventoDB = new UsuarioDBconexion( this);

        selectedManif = new boolean[manifArray.length];
        Objects.requireNonNull(binding.eventoManif).setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Evento.this);

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
                        binding.eventoManif.setText(manif);
                        //Toast.makeText(Activity_Evento.this, manif, Toast.LENGTH_LONG).show();
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
                            binding.eventoManif.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        Objects.requireNonNull(binding.eventoDiaEvento).setOnClickListener(v-> {

            final Calendar c = Calendar.getInstance();

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);



            DatePickerDialog dialog = new DatePickerDialog(
                    Activity_Evento.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datepicker, int year, int month, int dayOfMonth) {
                    fecha =dayOfMonth + "/" + (month + 1) + "/" + year;
                    binding.eventoDiaEvento.setText(fecha);
                }
            } , year, month, day) ;

            dialog.show();

        });

        Objects.requireNonNull(binding.eventoHoraEvento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                hora = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minuto = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Activity_Evento.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker,  int hourOfDay, int minute) {
                        hora = hourOfDay;
                        String amPm;

                        // Determine AM or PM and adjust hour
                        if (hora == 0) {
                            hora += 12;
                            amPm = "AM";
                        } else if (hora == 12) {
                            amPm = "PM";
                        } else if (hora > 12) {
                            hora -= 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }

                        // Format hour and minute for display
                        String formattedHour = (hora < 10) ? "0" + hora : String.valueOf(hora);
                        String formattedMinute = (minuto < 10) ? "0" + minuto : String.valueOf(minuto);

                        // Display the selected time
                        horaEvento = formattedHour + " : " + formattedMinute + " " + amPm;
                        binding.eventoHoraEvento.setText(horaEvento);
                    }
                }, hora, minuto, false);
                mTimePicker.setTitle("Elija Hora");
                mTimePicker.show();

            }
        });

        Objects.requireNonNull(binding.cancelButton).setOnClickListener(v -> {
            clearForm(findViewById(R.id.activity_evento));
            SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
            String id = sp.getString("user", "");
            Intent intent;
            if (id.isEmpty()) {
                intent = new Intent(Activity_Evento.this, Login.class);
            }else{
                intent = new Intent(Activity_Evento.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        });

        Objects.requireNonNull(binding.saveButton).setOnClickListener(v -> {
            fecha = binding.eventoDiaEvento.getText().toString();
            horaEvento = binding.eventoHoraEvento.getText().toString();

            SharedPreferences sp=getSharedPreferences("clave", Context.MODE_PRIVATE);
            String id = sp.getString("user", "");
            Evento nuevo=new Evento(fecha,horaEvento,manif, farm );
            if (id.isEmpty()){
                Intent intent = new Intent(Activity_Evento.this, Login.class);
                startActivity(intent);
                finish();
            }else if (fecha.isEmpty() || horaEvento.isEmpty()|| manif.isEmpty()||farm.isEmpty()) {
                Toast.makeText(Activity_Evento.this, "Necesita llenar campos obligatorios", Toast.LENGTH_SHORT).show();
            } else if  (-1 != EventoDB.GuardarEvento(nuevo,id)){
                Toast.makeText(Activity_Evento.this, "Evento registrado exitosamente", Toast.LENGTH_SHORT).show();
                clearForm(findViewById(R.id.activity_evento));
                Intent intent = new Intent(Activity_Evento.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(Activity_Evento.this,"Error en registro",Toast.LENGTH_SHORT).show();
            }
        });

        selectedFarm= new boolean[farmArray.length];
        Objects.requireNonNull(binding.farmacos).setOnClickListener(view -> {

            // Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Evento.this);

            // set title
            builder.setTitle("Elija farmacos");

            // set dialog non cancelable
            builder.setCancelable(false);

            builder.setMultiChoiceItems(farmArray, selectedFarm, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    // check condition
                    if (b) {
                        // when checkbox selected
                        // Add position  in lang list
                        farmList.add(i);
                        // Sort array list
                        Collections.sort(farmList);
                    } else {
                        // when checkbox unselected
                        // Remove position from langList
                        farmList.remove(Integer.valueOf(i));
                    }
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Initialize string builder
                    StringBuilder stringBuilder = new StringBuilder();
                    // use for loop
                    for (int j = 0; j < farmList.size(); j++) {
                        // concat array value
                        stringBuilder.append(farmKey[farmList.get(j)]);
                        // check condition
                        if (j != farmList.size() - 1) {
                            // When j value  not equal
                            // to lang list size - 1
                            // add comma
                            stringBuilder.append(", ");
                        }

                    }
                    // set text on textView
                    farm = stringBuilder.toString();
                    binding.farmacos.setText(farm);
                    //Toast.makeText(Activity_Evento.this, farm, Toast.LENGTH_LONG).show();
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
                        binding.eventoManif.setText("");
                    }
                }
            });
            // show dialog
            builder.show();
        });


//        RadioButton r1 = findViewById(R.id.radio24h);
//        RadioButton r2= findViewById(R.id.radio12h);
//        RadioButton r3= findViewById(R.id.radio8h);
//
//        Objects.requireNonNull(binding.CBZ).setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                Objects.requireNonNull(binding.CBZmg).setVisibility(View.VISIBLE);
//                r1.setVisibility(View.VISIBLE);
//                r2.setVisibility(View.VISIBLE);
//                r3.setVisibility(View.VISIBLE);
//            } else {
//                Objects.requireNonNull(binding.CBZmg).setVisibility(View.GONE);
//                r1.setVisibility(View.GONE);
//                r2.setVisibility(View.GONE);
//                r3.setVisibility(View.GONE);
//
//            }
//        });

    }

   /* private void checkFarm(){
        StringBuilder stbf = new StringBuilder();
        StringBuilder stbd = new StringBuilder();
        String temp;
        if (binding.CBZ.isChecked()){
            stbd.append("CBZ,");
            temp = binding.CBZmg.getText().toString()+",";
            stbd.append(temp);
        }
        farm = stbf.toString();
        dosis = stbd.toString();
    }*/


    private void clearForm(@NonNull ViewGroup viewById) {
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




//    private void checkManif(ViewGroup viewById){
//        if (binding.eventoConciencia.isChecked()) manif = "Perdida de la conciencia";
//        if (binding.eventoDesorientado.isChecked()) manif = "Sensación de desorentación";
//        if (binding.eventoVisibilidad.isChecked()) manif = "Trastornos visuales";
//        if (binding.eventoAudivilidad.isChecked()) manif = "Trastornos auditivos";
//        if (binding.eventoSensorial.isChecked()) manif = "Trastorno sensorial del cuerpo";
//        if (binding.eventoOlfato.isChecked()) manif = "Trastornos olfatorios";
//        if (binding.eventoMovimiento.isChecked()) manif = "Movimiento en manos o pies";
//        if (binding.eventoNOmovimiento.isChecked()) manif = "Ausencia de moviento";
//        if (binding.eventoRelajVesicula.isChecked()) manif = "Relajación de esfínter Vesical";
//        if (binding.eventoRelajAnal.isChecked()) manif = "Relajación de esfínter Anal";
//        if (binding.eventoRigidez.isChecked()) manif = "Rigidez de manos y/o pies";
//        if (binding.eventoRelajMusculos.isChecked()) manif = "Relajación de musculos";
//        if (binding.eventoSacudidas.isChecked()) manif = "Sacudidas de todo o parte del cuerpo";
//    }






