package SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import SQL.UsuarioToSQL.EntradaUsuario;

public class UsuarioDBconexion extends SQLiteOpenHelper {
    private static final String DB_NAME="usuario.db";
    private static final int DB_VERSION=2;

    public UsuarioDBconexion(Context context) {

        super(context, "usuario.db", null, DB_VERSION);
    }

     @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + EntradaUsuario.NOMBRE_TABLA + " ("
                 + EntradaUsuario._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                 + EntradaUsuario.ID + " TEXT UNIQUE, "
                 + EntradaUsuario.NOMBRE + " TEXT NOT NULL UNIQUE, "
                 + EntradaUsuario.CONTRASENA + " TEXT NOT NULL "
                 + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //codigo sql
        db.execSQL("DROP TABLE IF EXISTS " + EntradaUsuario.NOMBRE_TABLA);
        onCreate(db);
    }

    public long GuardarUsuario(Usuario usuario) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = -1;
        try {
            id = sqLiteDatabase.insert(EntradaUsuario.NOMBRE_TABLA, null, usuario.toContentValues());
        } catch (Exception e) {
            Log.e("DB", "Error al guardar el usuario", e);
        }
        return id;
    }

    public void leerUsuario() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] projection = {
                    UsuarioToSQL.EntradaUsuario.ID,
                    UsuarioToSQL.EntradaUsuario.NOMBRE,
                    UsuarioToSQL.EntradaUsuario.CONTRASENA,
            };

            cursor = db.query(
                    UsuarioToSQL.EntradaUsuario.NOMBRE_TABLA,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.NOMBRE));
                String contrasena = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.CONTRASENA));

                Log.d("DB", "Usuario: " + nombre + " Contraseña: " + contrasena + ", ID interno: " + id);
            }

        } catch (Exception e) {
            Log.e("DB", "Error al leer pacientes", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public boolean checkUser(String user)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Usuario where ID = ?", new String[]{user});

        boolean b = cursor.getCount() > 0;
        cursor.close();
        return b;
    }

    public boolean checkUserPass(String user, String pass)
    {

        boolean b;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Usuario where ID = ? AND Contrasena = ?", new String[]{user, pass});

        b = cursor.getCount() > 0;
        cursor.close();
        return b;

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer){
        onUpgrade(db, oldVer, newVer);
    }
}
