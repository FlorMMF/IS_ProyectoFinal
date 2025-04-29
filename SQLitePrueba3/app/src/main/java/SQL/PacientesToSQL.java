package SQL;

import android.provider.BaseColumns;

public class PacientesToSQL {
    public static abstract class EntradaPaciente implements BaseColumns {
        public static final String NOMBRE_TABLA ="pacientes";

        public static final String ID = "id";
        public static final String NOMBRE = "name";
        public static final String APELLIDO = "apellido";
        public static final String TELEFONO = "Tel";
    }
}
