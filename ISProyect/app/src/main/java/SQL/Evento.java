package SQL;


import android.content.ContentValues;

import java.util.UUID;

public class Evento {

    private final String id;

    private final String fecha;
    private final String hora;
    private final String manifestacionesSeleccionadas;
    private final String farmacosSeleccionados;

    // Constructor
    public Evento(String fecha, String hora, String manifestacionesSeleccionadas, String farmacosSeleccionados) {
        this.id = UUID.randomUUID().toString();
        this.fecha = fecha;
        this.hora = hora;
        this.manifestacionesSeleccionadas = manifestacionesSeleccionadas;
        this.farmacosSeleccionados = farmacosSeleccionados;
    }

    public ContentValues toContentValues(String usuario) {
        ContentValues values = new ContentValues();

        values.put(EventoToSQL.EntradaEvento.ID, id);
        values.put(EventoToSQL.EntradaEvento.USER, usuario);
        values.put(EventoToSQL.EntradaEvento.FECHA, fecha);
        values.put(EventoToSQL.EntradaEvento.HORA, hora);
        if(manifestacionesSeleccionadas!=null){
            values.put(EventoToSQL.EntradaEvento.MANIFESTACIONES, manifestacionesSeleccionadas);
        }else{
            values.put(EventoToSQL.EntradaEvento.MANIFESTACIONES, " ");
        }
        if(farmacosSeleccionados!=null){
            values.put(EventoToSQL.EntradaEvento.FARMACOS, farmacosSeleccionados);
        }else{
            values.put(EventoToSQL.EntradaEvento.FARMACOS, " ");
        }

        return values;
    }
}
