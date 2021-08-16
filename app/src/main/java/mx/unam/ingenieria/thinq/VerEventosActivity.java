package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VerEventosActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    private SQLiteDatabase db;

    private ListView listView;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_eventos);

        //instanciamos nuestras variables
        listView =(ListView)findViewById(R.id.ltvListaEventos); //le agregamos el escuchador de eventos
        listView.setOnItemLongClickListener(this);

        //obtendremos los datos que nos estan enviando desde la actividad que se mando llamar esta actividad
        Bundle bundle = getIntent().getExtras();
        int dia = 0, mes = 0, anio = 0;

        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes"); // estos datos se los mandaremos inicialmente a las cajas de texto
        anio = bundle.getInt("anio");

        //concatenamos los datos
        String cadena = dia +" - " + mes +" - " + anio;

        //vamos a abrir y nos conectaremos a nuestra base de datos en modo "lectura"
        //creamos un objeto del tipo BDSQLITE y creamos la instancia a esa clase
        BDSQLite bdsqLite = new BDSQLite(getApplicationContext(), "Agenda", null, 1);
        db = bdsqLite.getReadableDatabase();

        //preparamos nuestra consulta para obtener los datos
        String sql = "Select * from eventos where fechadesde ='" + cadena + "'";

        //declaramos un objeto de tipo cursor que nos va a servir para almacenar los registros que nos devuelva la consulta
        Cursor c;
        //declaramos variables temporales para caudno tengamos los datos lo almecenemos temporalmente en estas variables
        String nombre, fechadesde, fechahasta, descripcion, ubicacion;
        try {
            //hacemos la consulta y el dato devuelto o los registros que nos devuelvan lo guardamos en el cursor
            c = db.rawQuery(sql, null);

            //instanciamos nuestro arrayAdapter
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);//con esto le decimos que estamos creando una simple lista

            //comparamos que haya datos que leer, que no este vacio y hacemos un recorrido
            if(c.moveToFirst()){
                do {
                    //en el recorrido, en cada posicion la obtenemos de esta manera
                    //observamos que estará en cada cosa
                    nombre = c.getString(1);
                    ubicacion = c.getString(2);
                    fechadesde = c.getString(3); //los datos que vamos a mostrar en la lista
                    fechahasta = c.getString(5);
                    descripcion = c.getString(7);

                    //concatenamos los arreglos de los datos
                    arrayAdapter.add(nombre + ", " + ubicacion + ", " + fechadesde + ", " + fechahasta + ", " + descripcion);

                }while(c.moveToNext());
                listView.setAdapter(arrayAdapter); //mostramos los datos en el list view
            }else {
                this.finish();
            }

        }catch (Exception ex){
            Toast.makeText(getApplication(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //tenemos que desplegar un dialogo para que el usuario tambien pueda eliminar el evento
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        //creamos un Charsequence para las opciones de dos posiciones
        CharSequence []items = new CharSequence[2];
        items[0] = "Eliminar evento";
        items[1] = "Cancelar";

        // preparamos la ventana desplegar
        builder1.setTitle("Eliminar evento")
                .setItems(items, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            //eliminar el evento
                            eliminar(parent.getItemAtPosition(which).toString());//le pasamos el datos que seleccionamos y informamos al usuario que ya se ha eliminado su dato
                        }
                    }
                });
        AlertDialog dialog = builder1.create();//creamos el dialogo
        dialog.show(); //desplegamos el dialogo
        return false;
    }

    //creamos un metodo para eliminar eventos
    private void eliminar (String dato){ //recibirá el dato a eliminar

        //concatenaremos todos los registros, viendo que columnas tenemos en la basde datos


        //para eliminar el valor desde la base de datos
        String []datos = dato.split(", ");//se crea un arreglo en donde se dividen los datos que le llegan a la funcion donde tengan coma y espacio

        String sql = "delete from eventos where nombreEvento ='"+datos[0]+"' and" +
                "ubicacion='"+datos[1]+"' and fechadesde='"+datos[2]+"' and " +
                "fechahasta='"+datos[3]+"' and descripcion='"+datos[4]+"'";

        //String sql = "delete from eventos where concat(nombreEvento,', ', ubicacion,', '," + "fechadesde,', ', fechahasta,', ', descripcion) ='" + dato + "'";

        try {
            //primero eliminamos el valor en la cadena y despues en el arreglo (arrayAdapter)
            arrayAdapter.remove(dato);

            //cargamos el arrayAdapter al listView
            listView.setAdapter(arrayAdapter);

            //instruccion para eliminar desde la base de datos
            db.execSQL(sql);

            //informamos al usuario que ya se ha eliminado su dato
            Toast.makeText(getApplication(), "Evento eliminado", Toast.LENGTH_SHORT).show();
            finish();


        }catch (Exception ex){
            Toast.makeText(getApplication(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}