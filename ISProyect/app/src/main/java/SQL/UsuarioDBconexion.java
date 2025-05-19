package SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import SQL.UsuarioToSQL.EntradaUsuario;

public class UsuarioDBconexion extends SQLiteOpenHelper {
    private static final String DB_NAME="usuario.db";
    private static final int DB_VERSION=5;

    public UsuarioDBconexion(Context context) {

        super(context, "usuario.db", null, DB_VERSION);
    }

     @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + EntradaUsuario.NOMBRE_TABLA + " ("
                 + EntradaUsuario._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                 + EntradaUsuario.ID + " TEXT UNIQUE, "
                 + EntradaUsuario.USER + " TEXT NOT NULL UNIQUE, "
                 + EntradaUsuario.CONTRASENA + " TEXT NOT NULL, "
                 + EntradaUsuario.NOMBRE + " TEXT NOT NULL, "
                 + EntradaUsuario.APELLIDO + " TEXT NOT NULL, "
                 + EntradaUsuario.NACIMIENTO + " TEXT NOT NULL, "
                 + EntradaUsuario.GENERO + " TEXT NOT NULL, "
                 + EntradaUsuario.EPILEPSIA + " TEXT NOT NULL"
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
                    UsuarioToSQL.EntradaUsuario.USER,
                    UsuarioToSQL.EntradaUsuario.CONTRASENA,
                    UsuarioToSQL.EntradaUsuario.NOMBRE,
                    UsuarioToSQL.EntradaUsuario.APELLIDO,
                    UsuarioToSQL.EntradaUsuario.NACIMIENTO,
                    UsuarioToSQL.EntradaUsuario.GENERO,
                    UsuarioToSQL.EntradaUsuario.EPILEPSIA,
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
                String user = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.USER));
                String contrasena = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.CONTRASENA));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.NOMBRE));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.APELLIDO));
                String nacimiento = cursor.getString(cursor.getColumnIndexOrThrow(EntradaUsuario.NACIMIENTO));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioToSQL.EntradaUsuario.GENERO));
                String tipoEpilepsia = cursor.getString(cursor.getColumnIndexOrThrow(EntradaUsuario.EPILEPSIA));

                Log.d("DB", "Usuario: " + user + " Contraseña: " + contrasena + "nombre: "+nombre+" "+apellido+"Fecha de nacimiento: "+nacimiento+"genero: "+genero+"tipo Epilepsia: "+tipoEpilepsia+", ID interno: " + id);
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

        String columns[] = new String[]{EntradaUsuario.USER};
        String selection = EntradaUsuario.USER + " LIKE ?"; // WHERE nombre LIKE ?
        String selectionArgs[] = new String[]{user};

        Cursor c = sqLiteDatabase.query(
                EntradaUsuario.NOMBRE_TABLA,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean b = c.getCount() > 0;
        c.close();
        return b;
    }

    public boolean checkUserPass(String user, String pass)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean b;
        String columns[] = new String[]{EntradaUsuario.USER};
        String selection = EntradaUsuario.USER + " = ? AND " + EntradaUsuario.CONTRASENA + " = ?"; // WHERE contraseña LIKE ?
        String selectionArgs[] = new String[]{user, pass};

        Cursor c = sqLiteDatabase.query(
                EntradaUsuario.NOMBRE_TABLA,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        b = c.getCount() > 0;
        c.close();
        return b;

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer){
        onUpgrade(db, oldVer, newVer);
    }
}
