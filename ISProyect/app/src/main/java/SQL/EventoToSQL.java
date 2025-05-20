package SQL;

import android.provider.BaseColumns;

public class EventoToSQL {

    public static abstract class EntradaEvento implements BaseColumns {
        public static final String NOMBRE_TABLA ="Evento";
        public static final String ID = "id";
        public static final String USER="user";
        public static final String FECHA="fecha";
        public static final String HORA="hora";

        public static final String MANIFESTACIONES="manifestaciones";

        public static final String FARMACOS="farmacos";
    }
}
