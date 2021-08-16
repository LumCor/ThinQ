package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Activity_CrearHorario extends AppCompatActivity {

    /**
     *Declaracion de todos los objetos que se tomaran en cuenta
    */

    EditText txtTitulo;
    EditText txtContenido;
    Button btnCrearDato;
    EditText editTextGrupo;

    RadioButton rbtnLu;
    RadioButton rbtnMa;
    RadioButton rbtnMi;
    RadioButton rbtnJu;
    RadioButton rbtnVi;
    RadioButton rbtnSa;
    RadioButton rbtnDo;

    View listViewLu;
    View listViewMa;
    View listViewMi;
    View listViewJu;
    View listViewVi;
    View listViewSa;
    View listViewDo;

    TextView[] txtvIn = new TextView[7]; //Para indicar la hora de inicio

    TextView [] txtvFi = new TextView[7]; //Para indicar la hora fin


    int [] i={0,0,0,0,0,0,0}; //Arreglo que depende de los dias que se seleccionaran


    FirebaseFirestore mFirestore; //Instancia de la base de datos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__crear_horario);

        txtTitulo=findViewById(R.id.editTextTitulo);
        txtContenido=findViewById(R.id.editTextContenido);
        btnCrearDato=findViewById(R.id.btnGuardarDatos);
        editTextGrupo=findViewById(R.id.editTextGrupo);
        mFirestore=FirebaseFirestore.getInstance();

        //Radio Button
        rbtnLu=findViewById(R.id.rbtnLu);
        rbtnMa=findViewById(R.id.rbtnMa);
        rbtnMi=findViewById(R.id.rbtnMi);
        rbtnJu=findViewById(R.id.rbtnJu);
        rbtnVi=findViewById(R.id.rbtnVi);
        rbtnSa=findViewById(R.id.rbtnSa);
        rbtnDo=findViewById(R.id.rbtnDo);

        //List Viev
        listViewLu=findViewById(R.id.layoutLu);
        listViewMa=findViewById(R.id.layoutMa);
        listViewMi=findViewById(R.id.layoutMi);
        listViewJu=findViewById(R.id.layoutJu);
        listViewVi=findViewById(R.id.layoutVi);
        listViewSa=findViewById(R.id.layoutSa);
        listViewDo=findViewById(R.id.layoutDo);

        //Text View inicio
        txtvIn[0]=findViewById(R.id.txtHoraInLu);
        txtvIn[1]=findViewById(R.id.txtHoraInMa);
        txtvIn[2]=findViewById(R.id.txtHoraInMi);
        txtvIn[3]=findViewById(R.id.txtHoraInJu);
        txtvIn[4]=findViewById(R.id.txtHoraInVi);
        txtvIn[5]=findViewById(R.id.txtHoraInSa);
        txtvIn[6]=findViewById(R.id.txtHoraInDo);

        //Text View fin
        txtvFi[0]=findViewById(R.id.txtHoraFinLu);
        txtvFi[1]=findViewById(R.id.txtHoraFinMa);
        txtvFi[2]=findViewById(R.id.txtHoraFinMi);
        txtvFi[3]=findViewById(R.id.txtHoraFinJu);
        txtvFi[4]=findViewById(R.id.txtHoraFinVi);
        txtvFi[5]=findViewById(R.id.txtHoraFinSa);
        txtvFi[6]=findViewById(R.id.txtHoraFinDo);



        /**
         * Los siguientes OnClickListener serviran para agregar listView con respecto a cada dia
         * estos dependeran de los RadioButton que se hayan seleccionado
        */
        rbtnLu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[0]==0) {
                    listViewLu.setVisibility(View.VISIBLE);
                    i[0]=1;
                }
                else {
                    i[0]=0;
                    listViewLu.setVisibility(View.GONE);
                    rbtnLu.setChecked(false);
                }
            }
        });

        rbtnMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[1]==0) {
                    listViewMa.setVisibility(View.VISIBLE);
                    i[1]=1;
                }
                else {
                    i[1]=0;
                    listViewMa.setVisibility(View.GONE);
                    rbtnMa.setChecked(false);
                }
            }
        });

        rbtnMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[2]==0) {
                    listViewMi.setVisibility(View.VISIBLE);
                    i[2]=1;
                }
                else {
                    i[2]=0;
                    listViewMi.setVisibility(View.GONE);
                    rbtnMi.setChecked(false);
                }
            }
        });

        rbtnJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[3]==0) {
                    listViewJu.setVisibility(View.VISIBLE);
                    i[3]=1;
                }
                else {
                    i[3]=0;
                    listViewJu.setVisibility(View.GONE);
                    rbtnJu.setChecked(false);
                }
            }
        });

        rbtnVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[4]==0) {
                    listViewVi.setVisibility(View.VISIBLE);
                    i[4]=1;
                }
                else {
                    i[4]=0;
                    listViewVi.setVisibility(View.GONE);
                    rbtnVi.setChecked(false);
                }
            }
        });

        rbtnSa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[5]==0) {
                    listViewSa.setVisibility(View.VISIBLE);
                    i[5]=1;
                }
                else {
                    i[5]=0;
                    listViewSa.setVisibility(View.GONE);
                    rbtnSa.setChecked(false);
                }
            }
        });

        rbtnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i[6]==0) {
                    listViewDo.setVisibility(View.VISIBLE);
                    i[6]=1;
                }
                else {
                    i[6]=0;
                    listViewDo.setVisibility(View.GONE);
                    rbtnDo.setChecked(false);
                }
            }
        });


        /**
         * Este OnClickListener es para crear el dato, on ayuda del metodo crearDatos();
         */
        btnCrearDato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDatos();
            }
        });


    }


    /**
     * Es el metodo que se encargara de subir la informacion recabada de los edittext en la base de datos
     */
    private void crearDatos(){
        String asignatura = txtTitulo.getText().toString();
        String notas = txtContenido.getText().toString();
        String grupo = editTextGrupo.getText().toString();
        String [][] horario =new String[7][2]; //Contendra los dias con la hora de inicio y fin
        String [] dias = new String[7];
        Boolean id=true, id2=false; //Identificadores para detectar que los campos esten llenos

        //Para pasar las horas que se colocaron es nuestro nuevo arreglo horario
        for (int j=0; j<7; j++){
            if (i[j]==1){
                horario[j][0]=txtvIn[j].getText().toString();
                horario[j][1]=txtvFi[j].getText().toString();
                id2=true;

                if (horario[j][0].isEmpty() && horario[j][1].isEmpty()){ //Para verificar que se hayan puesto todas las horas
                    id=false;
                }
            }
        }


        if (!asignatura.isEmpty() && !notas.isEmpty() && !grupo.isEmpty() && id && id2) { //Una vez que todos los campos esten llenos
            int grupov;

            grupov=Integer.parseInt(grupo);
            Map<String, Object> map = new HashMap<>(); //Un mapa que contrendra todas nuestras variables
            map.put("Asignatura", asignatura);
            map.put("Notas", notas);
            map.put("Grupo", grupov);

            //En esta parte se agregara por dia aquellas horas que se hayan escrito y se agregara
            //en un arreglo que cada posicion es un dia
            for (int j = 0; j < 7; j++) {
                if (i[j] == 1) {
                    switch (j) {
                        case 0:
                            dias[j]=("Lu " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                        case 1:
                            dias[j]=("Ma " + horario[j][0] + "-" + horario[j][1] + ", " );
                            break;
                        case 2:
                            dias[j]=("Mi " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                        case 3:
                            dias[j]=("Ju " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                        case 4:
                            dias[j]=("Vi " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                        case 5:
                            dias[j]=("Sa " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                        case 6:
                            dias[j]=("Do " + horario[j][0] + "-" + horario[j][1] + ", ");
                            break;
                    }
                }
                else {
                    dias[j]="";
                }
            }


            map.put("Dias", dias[0] + dias[1] + dias[2] + dias[3] + dias[4] + dias[5] + dias[6]);

            mFirestore.collection("Materias").document().set(map); //Se agrega a la base de datos

            Toast.makeText(getApplicationContext(), "Registro exitosa", Toast.LENGTH_SHORT).show();

            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "LLena todos los datos solicitados", Toast.LENGTH_SHORT).show();
        }
    }
}