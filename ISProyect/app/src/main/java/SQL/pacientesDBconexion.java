package SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import SQL.PacientesToSQL.EntradaPaciente;

public class pacientesDBconexion extends SQLiteOpenHelper {
    private static final String DB_NAME="pacientes.db";
    private static final int DB_VERSION=2;

    public pacientesDBconexion(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

     @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + EntradaPaciente.NOMBRE_TABLA + " ("
                 + EntradaPaciente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 + EntradaPaciente.ID + " TEXT UNIQUE, "
                 + EntradaPaciente.NOMBRE + " TEXT NOT NULL, "
                 + EntradaPaciente.APELLIDO + " TEXT NOT NULL, "
                 + EntradaPaciente.TELEFONO + " TEXT NOT NULL"
                 + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //codigo sql
        db.execSQL("DROP TABLE IF EXISTS " + EntradaPaciente.NOMBRE_TABLA);
        onCreate(db);
    }

    public long GuardarPaciente(Paciente paciente) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = -1;
        try {
            id = sqLiteDatabase.insert(EntradaPaciente.NOMBRE_TABLA, null, paciente.toContentValues());
        } catch (Exception e) {
            Log.e("DB", "Error al guardar el paciente", e);
        }
        return id;
    }

    public void leerPacientes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] projection = {
                    PacientesToSQL.EntradaPaciente.ID,
                    PacientesToSQL.EntradaPaciente.NOMBRE,
                    PacientesToSQL.EntradaPaciente.APELLIDO,
                    PacientesToSQL.EntradaPaciente.TELEFONO
            };

            cursor = db.query(
                    PacientesToSQL.EntradaPaciente.NOMBRE_TABLA,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(PacientesToSQL.EntradaPaciente.ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(PacientesToSQL.EntradaPaciente.NOMBRE));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow(PacientesToSQL.EntradaPaciente.APELLIDO));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow(PacientesToSQL.EntradaPaciente.TELEFONO));

                Log.d("DB", "Paciente: " + nombre + " " + apellido + ", Tel: " + telefono + ", ID interno: " + id);
            }

        } catch (Exception e) {
            Log.e("DB", "Error al leer pacientes", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }



    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer){
        onUpgrade(db, oldVer, newVer);
    }
}
