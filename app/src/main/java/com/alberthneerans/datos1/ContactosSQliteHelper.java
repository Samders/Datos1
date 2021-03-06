package com.alberthneerans.datos1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alberth Neerans on 19/10/2016.
 */
public class ContactosSQliteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Contactos (" +
            "id         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre     TEXT," +
            "telefono   INTEGER," +
            "correo     TEXT)";

    public ContactosSQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        db.execSQL(sqlCreate);
    }

}
