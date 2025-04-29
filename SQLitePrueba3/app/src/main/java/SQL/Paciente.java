package SQL;

import android.content.ContentValues;

import java.util.UUID;

public class Paciente {
        private final String id;
        private final String nombre;
        private final String apellido;
        private final String Tel;

        public Paciente(String nombre, String apellido, String Tel) {
            this.id = UUID.randomUUID().toString();
            this.nombre = nombre;
            this.apellido = apellido;
            this.Tel = Tel;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public String getTel() {
            return Tel;
        }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(PacientesToSQL.EntradaPaciente.ID, id);
        values.put(PacientesToSQL.EntradaPaciente.NOMBRE, nombre);
        values.put(PacientesToSQL.EntradaPaciente.APELLIDO, apellido);
        values.put(PacientesToSQL.EntradaPaciente.TELEFONO, Tel);
        return values;
    }

}
