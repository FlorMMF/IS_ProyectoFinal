package SQL;

import android.content.ContentValues;

import java.util.UUID;

public class Usuario {
        private final String id;
        private final String user;
        private final String contrasena;

        private final String nombre;

        private final String apellido;

        private final String nacimiento;

        private final String genero;

        private final String epilepsia;

        public Usuario(String user, String contrasena, String nombre, String apellido, String nacimiento,String genero, String epilepsia) {
            this.id = UUID.randomUUID().toString();
            this.user = user;
            this.contrasena = contrasena;
            this.nombre =nombre;
            this.apellido=apellido;
            this.nacimiento =nacimiento;
            this.genero=genero;
            this.epilepsia =epilepsia;
        }

        public String getId() {
            return id;
        }

        public String getUser() {
            return user;
        }

        public String getContrasena() {
            return contrasena;
        }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(UsuarioToSQL.EntradaUsuario.ID, id);
        values.put(UsuarioToSQL.EntradaUsuario.USER, user);
        values.put(UsuarioToSQL.EntradaUsuario.CONTRASENA, contrasena);
        values.put(UsuarioToSQL.EntradaUsuario.NOMBRE, nombre);
        values.put(UsuarioToSQL.EntradaUsuario.APELLIDO, apellido);
        values.put(UsuarioToSQL.EntradaUsuario.NACIMIENTO, nacimiento);
        values.put(UsuarioToSQL.EntradaUsuario.GENERO, genero);
        values.put(UsuarioToSQL.EntradaUsuario.EPILEPSIA, epilepsia);
        return values;
    }

}
