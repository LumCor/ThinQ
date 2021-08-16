package mx.unam.ingenieria.thinq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_EditTarea extends AppCompatActivity {
    /**
     * En este activity el usuario ingresa los datos para crear una nueva tarea
     */

    EditText edtMateria;
    EditText edtDescripcion;
    Switch swchFacil;
    Switch swchMasomenos;
    Switch swchDificil;
    Button btnCancelar;
    Button btnAgregarTarea;
    int dif =0;

    FirebaseFirestore myFirestore;//Instancia de la base de datos

    /**
     * Declaración de los elementos de "edita_tarea" y se inicia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edita_tarea);
        myFirestore=FirebaseFirestore.getInstance();

        edtMateria= findViewById(R.id.edtMateria);
        swchFacil= findViewById(R.id.swchFacil);
        swchMasomenos= findViewById(R.id.swchMasomenos);
        swchDificil= findViewById(R.id.swchDificil);
        edtDescripcion= findViewById(R.id.edtDescripcion);
        btnAgregarTarea= findViewById(R.id.btnAgregarTarea);
        btnCancelar= findViewById(R.id.btnCancelar);

        /**
         * Se implementan los Switch para identificar la dificultad que el usuasio selecciona.
         * Para identificarlo se sa la variable "dif" que guarda un numero segun la dificultad.
         */

        swchFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swchFacil.isChecked()){
                    swchMasomenos.setVisibility(View.GONE);
                    swchDificil.setVisibility(View.GONE);
                    dif=1;
                }else {
                    swchMasomenos.setVisibility(View.VISIBLE);
                    swchDificil.setVisibility(View.VISIBLE);
                    dif=0;
                }

            }
        });

        swchMasomenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swchMasomenos.isChecked()){
                    swchFacil.setVisibility(View.GONE);
                    swchDificil.setVisibility(View.GONE);
                    dif=2;
                }else {
                    swchFacil.setVisibility(View.VISIBLE);
                    swchDificil.setVisibility(View.VISIBLE);
                    dif=0;
                }

            }
        });

        swchDificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swchDificil.isChecked()){
                    swchMasomenos.setVisibility(View.GONE);
                    swchFacil.setVisibility(View.GONE);
                    dif=3;

                }else {
                    swchMasomenos.setVisibility(View.VISIBLE);
                    swchFacil.setVisibility(View.VISIBLE);
                    dif=0;
                }

            }
        });


        /**
         * Cuando se preciona "guardar", se inicia la función "guardarDatos()"
         * Cuando se preciona "cancelar", se regresa a la  vista anterior
         */
        btnAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * Obtiene los datos que el usuario ingresa en edit_tarea.xml y los envía a la base de datos
     */
    private void guardarDatos(){
        String materia = edtMateria.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String dificultad = "facil";
        Boolean ident = true;

        //Identifica si el usuario no ha seleccionado una dificultad
        if(dif == 0){
            ident = false;
        }

        //Verifica que todos los datos fueron llenados
        if(!materia.isEmpty() && !descripcion.isEmpty() && ident){

            //El switch asigna en la variable "dificultad" el tipo de dificultad
            switch (dif){
                case 1:
                    dificultad="facil";
                    break;
                case 2:
                    dificultad="masomenos";
                    break;
                case 3:
                    dificultad="dificil";
                    break;
            }

            Map<String, Object> map = new HashMap<>(); //Un mapa que contrendra todas la variables que se enviarán a las base
            map.put("materia", materia);
            map.put("descripcion", descripcion);
            map.put("dificultad", dificultad);

            myFirestore.collection("Tareas").document().set(map);//Envío de datos a la base

            Toast.makeText(getApplicationContext(), "Tarea registrada", Toast.LENGTH_SHORT).show();

            finish();

        }else{
            Toast.makeText(getApplicationContext(), "Falta lLenar todos los datos solicitados o selecciona una dificultad", Toast.LENGTH_SHORT).show();

        }
    }
}
