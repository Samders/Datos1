package com.alberthneerans.datos1;


        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Switch;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eNombre, eTelefono, eCorreo;

    String nombre, correo,telefono;
    Button bInsertar, bActualizar, bBorrar, bBuscar;


    ContentValues dataDB;
    SQLiteDatabase dbContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactosSQliteHelper contactos = new ContactosSQliteHelper(this, "ContactosBD", null, 1);
        dbContactos = contactos.getWritableDatabase();

        eNombre = (EditText) findViewById(R.id.eNombre);
        eTelefono = (EditText) findViewById(R.id.eTelefono);
        eCorreo = (EditText) findViewById(R.id.eMail);
        bInsertar = (Button) findViewById(R.id.bInsertar);
        bActualizar = (Button) findViewById(R.id.bActualizar);
        bBorrar = (Button) findViewById(R.id.bBorrar);
        bBuscar = (Button) findViewById(R.id.bBuscar);

        bInsertar.setOnClickListener(this);
        bActualizar.setOnClickListener(this);
        bBorrar.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        ;
    }

    @Override
    public void onClick(View view) {


        nombre = eNombre.getText().toString();
        correo = eCorreo.getText().toString();
        telefono = eTelefono.getText().toString();

        int id=view.getId();

        switch (id) {
            case R.id.bInsertar:
                dataDB = new ContentValues();
                dataDB.put("nombre", nombre);
                dataDB.put("correo", correo);
                dataDB.put("telefono", telefono);

                dbContactos.insert("Contactos", null, dataDB);
                //dbContactos.execSQL("INSERT INTO Contactos VALUES(null,'"+nombre+"','"+telefono+"', '"+correo+"')");
                break;

            case R.id.bActualizar:
                dataDB = new ContentValues();

                dataDB.put("correo", correo);
                dataDB.put("telefono", telefono);
                dbContactos.update("Contactos", dataDB, "nombre='"+nombre+"'",null);
                //dbContactos.execSQL("UDAPTE Contactos SET telefono='" + telefono + "', correo='" + correo + "'WHERE nombre='" + nombre + "'");
                break;
            case R.id.bBorrar:
                dbContactos.execSQL("DELETE FROM Contactos WHERE nombre='" + nombre + "'");
                break;
            case R.id.bBuscar:
                Cursor c = dbContactos.rawQuery("SELECT * FROM Contactos WHERE nombre='" + nombre + "'", null);
                if (c.moveToFirst()) {
                    eTelefono.setText(c.getString(2));
                    eCorreo.setText(c.getString(3));
                }
                break;

        }

    }

}

