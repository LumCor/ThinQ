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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_EditarMateria extends AppCompatActivity {


    /**
     *Este activity es muy parecido al CrearHorario, lo unico que cambia es que recupera informacion
     *por medio de articulosId para asi poder editar el dato seleccionado.
     *
     *Declaracion de todos los objetos que se tomaran en cuenta
     */

    private String articuloId; //Con ayuda de esta variable recuperaremos informacion de la base de datos
    private FirebaseFirestore mFirestore;

    EditText txtTitulo;
    EditText txtGrupo;
    EditText txtContenido;
    Button btnEditar;

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

    TextView[] txtvIn = new TextView[7];
    TextView [] txtvFi = new TextView[7];
    int [] i={0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__editar_materia);

        mFirestore = FirebaseFirestore.getInstance();
        articuloId = getIntent().getStringExtra("articuloId"); //Para obtener el id en una nueva vista
        txtTitulo=findViewById(R.id.editTextTitulo);
        txtGrupo=findViewById(R.id.editTextGrupo);
        txtContenido=findViewById(R.id.editTextContenido);
        btnEditar=findViewById(R.id.btnEditarMateria);

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
         * Al igual que en CrearHorario:
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

        obtenerDatos(); //Metodo para recuperar informacion y poder mostrarla en los textView

        btnEditar.setOnClickListener(new View.OnClickListener() { //Si se da click en el boton, se actualizaran los datos
            @Override
            public void onClick(View v) {
                actualizarDatos(); //Metodo para guardar los nuevos datos escritos

            }
        });
    }

    /**
     * Metodo encargado de obtender la informacion seleccionada de la base de datos
     * y mostrarla en los respectivos textview
     */
    private void obtenerDatos(){
        mFirestore.collection("Materias").document(articuloId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){ //Si el documento existe...
                    String titulo = documentSnapshot.getString("Asignatura");
                    String contenido = documentSnapshot.getString("Notas");
                    Long var = documentSnapshot.getLong("Grupo"); //Para esta variable, se
                    //realizara un cateo ya que es un dato numerico y nosotros solamente queremos la
                    //parte entera
                    int var2= var.intValue();
                    String grupo = String.valueOf((var2));

                    txtTitulo.setText(titulo);
                    txtContenido.setText(contenido);
                    txtGrupo.setText(grupo);

                }

            }
        });

    }

    /**
     * Es el metodo que se encargara de subir la informacion recabada de los edittext en la base de datos
     * Se realiza el mismo proceso que en el Activity CrearHorario
     */
    private  void actualizarDatos(){
        String asignatura = txtTitulo.getText().toString();
        String notas = txtContenido.getText().toString();
        String grupo = txtGrupo.getText().toString();


        String [][] horario =new String[7][2];
        String [] dias = new String[7];
        Boolean id=true, id2=false;
        int grupov;
        grupov=Integer.parseInt(grupo);

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

        if (!asignatura.isEmpty() && !notas.isEmpty() && !grupo.isEmpty() && id && id2) {
            Map<String, Object> map = new HashMap<>();
            map.put("Asignatura", asignatura);
            map.put("Grupo", grupov);
            map.put("Notas", notas);

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
            mFirestore.collection("Materias").document(articuloId).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Los datos se actualizaron correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "LLena todos los datos solicitados", Toast.LENGTH_SHORT).show();
        }
    }





}