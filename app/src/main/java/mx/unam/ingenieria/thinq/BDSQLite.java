package mx.unam.ingenieria.thinq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLite extends SQLiteOpenHelper {

    //atributo de tipo cadena que va a contener nuestros atributos de la base datos
    private String sql = "create table eventos(" +
            "idEvento int identity," +
            "nombreEvento varchar(40) ," +
            "ubicacion varchar(60) ," +
            "fechadesde date," +
            "horadesde time," +
            "fechahasta date," +
            "horahasta time," +
            "descripcion varchar(60))";

    public BDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
