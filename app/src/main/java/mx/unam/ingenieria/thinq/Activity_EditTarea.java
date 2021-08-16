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
    EditText edtMateria;
    EditText edtDescripcion;
    Switch swchFacil;
    Switch swchMasomenos;
    Switch swchDificil;
    Button btnCancelar;
    Button btnAgregarTarea;
    int dif =0;


    FirebaseFirestore myFirestore;


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
                }Toast.makeText(getApplicationContext(), "Dificultad"+dif, Toast.LENGTH_SHORT).show();

            }
        });

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

    private void guardarDatos(){
        String materia = edtMateria.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String dificultad = "facil";
        Boolean ident = true;

        if(dif == 0){
            ident = false;
        }

        if(!materia.isEmpty() && !descripcion.isEmpty() && ident){
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

            Map<String, Object> map = new HashMap<>(); //Un mapa que contrendra todas nuestras variables
            map.put("materia", materia);
            map.put("descripcion", descripcion);
            map.put("dificultad", dificultad);

            myFirestore.collection("Tareas").document().set(map);

            Toast.makeText(getApplicationContext(), "Tarea registrada", Toast.LENGTH_SHORT).show();

            finish();

        }else{
            Toast.makeText(getApplicationContext(), "LLena todos los datos solicitados o selecciona una dificultad", Toast.LENGTH_SHORT).show();

        }


    }
}
