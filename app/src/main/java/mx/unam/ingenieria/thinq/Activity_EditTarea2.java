package mx.unam.ingenieria.thinq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_EditTarea2 extends AppCompatActivity {
    /**
     * En este activity el usuario ingresa los datos para editar una tarea
     */

    private String articuloId;
    private FirebaseFirestore myFirestore;//Instancia de la base de datos

    EditText edtMateria;
    EditText edtDescripcion;
    Switch swchFacil;
    Switch swchMasomenos;
    Switch swchDificil;
    Button btnCancelar;
    Button btnEditarTarea;
    int dif =0;

    /**
     * Declaración de los elementos de "edita_tarea2" y se inicia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edita_tarea2);

        myFirestore = FirebaseFirestore.getInstance();
        articuloId = getIntent().getStringExtra("articuloId");//obtiene la dirección del elemento que se quiere editar

        edtMateria= findViewById(R.id.edtMateria);
        swchFacil= findViewById(R.id.swchFacil);
        swchMasomenos= findViewById(R.id.swchMasomenos);
        swchDificil= findViewById(R.id.swchDificil);
        edtDescripcion= findViewById(R.id.edtDescripcion);
        btnEditarTarea= (Button)findViewById(R.id.btnEditarTarea);
        btnCancelar= (Button)findViewById(R.id.btnCancelar);

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

        cargarDatos();

        /**
         * Cuando se preciona "guardar", se inicia la función "guardarDatos()"
         * Cuando se preciona "cancelar", se regresa a la  vista anterior
         */
        btnEditarTarea.setOnClickListener(new View.OnClickListener() {
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
     * Carga los datos del emento que se quiere editar en el "edita_tarea2"
     */

    private void cargarDatos(){
        myFirestore.collection("Tareas").document(articuloId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String materia = documentSnapshot.getString("materia");
                    String descripcion = documentSnapshot.getString("descripcion");

                    edtMateria.setText(materia);
                    edtDescripcion.setText(descripcion);
                }

            }
        });
    }

    /**
     * Obtiene los nuevos datos que el usuario ingresa en edit_tarea2.xml y los actualiza en la base de datos
     */
    private void guardarDatos(){
        String materia = edtMateria.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String dificultad="facil";
        boolean ident=true;

        //Identifica si el usuario no ha seleccionado una dificultad
        if(dif == 0){
            ident = false;
        }

        if(!materia.isEmpty() && !descripcion.isEmpty() && ident){ //Verifica que todos los datos fueron llenados

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

            //Manda los nuevos datos en la dirección del elemento guardado
            myFirestore.collection("Tareas").document(articuloId).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();

                    finish();
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "LLena todos los datos solicitados o selecciona una dificultad", Toast.LENGTH_SHORT).show();

        }

    }

}
