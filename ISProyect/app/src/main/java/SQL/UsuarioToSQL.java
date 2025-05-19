package SQL;

import android.provider.BaseColumns;

public class UsuarioToSQL {
    public static abstract class EntradaUsuario implements BaseColumns {
        public static final String NOMBRE_TABLA ="Usuario";

        public static final String ID = "id";
        public static final String USER = "user";
        public static final String CONTRASENA = "contrasena";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String NACIMIENTO = "nacimiento";

        public static final String GENERO = "genero";
        public static final String EPILEPSIA = "epilepsia";
    }
}
