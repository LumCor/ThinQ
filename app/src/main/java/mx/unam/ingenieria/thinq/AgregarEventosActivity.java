package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarEventosActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nombreEvento, ubicacion, fechadesde, horadesde, fechahasta, horahasta;
    private EditText descripcion;

    private Button guardar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_eventos);

        nombreEvento = (EditText) findViewById(R.id.edtNombreEvento);
        ubicacion = (EditText) findViewById(R.id.edtUbicacion);
        fechadesde = (EditText) findViewById(R.id.edtFechaDesde);
        horadesde = (EditText) findViewById(R.id.edtHoraInicio);
        fechahasta = (EditText) findViewById(R.id.edtFechaHasta);
        horahasta = (EditText) findViewById(R.id.edtHoraHasta);
        descripcion = (EditText) findViewById(R.id.edtDescripcion);

        //vamos a obtener los parametros que se estan recibiendo desde la llamada de esta actividad, entonces hacemos:
        Bundle bundle = getIntent().getExtras();
        int dia = 0, mes = 0, anio = 0; //variables locales que nos ayudaran a almacenar los datos que se están recibiendo

        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes"); // estos datos se los mandaremos inicialmente a las cajas de texto
        anio = bundle.getInt("anio");

        // para mandarlos
        fechadesde.setText(dia +" - " + mes +" - " + anio);
        fechahasta.setText(dia +" - " + mes +" - " + anio);




        guardar = (Button) findViewById(R.id.btnGuardar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        //agregamos los escuchadores de eventos a los dos botones
        // para que al presionar cada uno de ellos se ejecute la instruccion necesaria
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //comparamos que opcion se está presionando (guardar o cancelar)
        if(nombreEvento==null && ubicacion==null && fechadesde==null && horadesde==null && fechahasta==null && horahasta==null && descripcion==null){
            Toast.makeText(getApplication(), "Llea todos los campos", Toast.LENGTH_SHORT).show();
        }
        else {
            if (v.getId() == guardar.getId()) {
                // guardar los datos de laa cajas de texto
                //creamos objeto de tipo getApplication, le pasamos el nombre de la base de datos, le mandamos un valor nulo y la version de la base de datos
                BDSQLite bd = new BDSQLite(getApplication(), "Agenda", null, 1);

                //le asignamos la apertura de la base de datos en modo de escritura
                SQLiteDatabase db = bd.getWritableDatabase();

                //sentencia para insertar los datos a la base de datos y especificamos en que columnas vamos a insertar
                String sql = "insert into eventos" +
                        "(nombreEvento, ubicacion, fechadesde,horadesde,fechahasta,horahasta," +
                        "descripcion) values('" +
                        nombreEvento.getText() +
                        "' , '" + ubicacion.getText() +
                        "' , '" + fechadesde.getText() +
                        "' , '" + horadesde.getText() +
                        "' , '" + fechahasta.getText() +
                        "' , '" + horahasta.getText() +
                        "' , '" + descripcion.getText() + "')"; //con esto preparamos la sentencia sql

                finish();
                Toast.makeText(getApplicationContext(), "Registro correcto " + sql, Toast.LENGTH_SHORT).show();

                try {
                    db.execSQL(sql);

                    //limpiar los campos
                    nombreEvento.setText("");
                    ubicacion.setText("");
                    fechadesde.setText("");
                    fechahasta.setText("");
                    horadesde.setText("");
                    horahasta.setText("");
                    descripcion.setText("");

                } catch (Exception e) {
                    Toast.makeText(getApplication(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            } else {
                finish();
            }
        }
    }
}