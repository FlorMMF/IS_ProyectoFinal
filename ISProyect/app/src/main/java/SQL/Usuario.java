package SQL;

import android.content.ContentValues;

import java.util.UUID;

public class Usuario {
        private final String id;
        private final String nombre;
        private final String contrasena;

        public Usuario(String nombre, String contrasena) {
            this.id = UUID.randomUUID().toString();
            this.nombre = nombre;
            this.contrasena = contrasena;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getContrasena() {
            return contrasena;
        }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(UsuarioToSQL.EntradaUsuario.ID, id);
        values.put(UsuarioToSQL.EntradaUsuario.NOMBRE, nombre);
        values.put(UsuarioToSQL.EntradaUsuario.CONTRASENA, contrasena);
        return values;
    }

}
