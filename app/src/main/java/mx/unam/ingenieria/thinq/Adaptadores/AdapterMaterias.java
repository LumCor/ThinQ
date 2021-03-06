package mx.unam.ingenieria.thinq.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import mx.unam.ingenieria.thinq.Activity_EditarMateria;
import mx.unam.ingenieria.thinq.Fragments.Horario_fragment;
import mx.unam.ingenieria.thinq.R;

/**
 *Es muy importante mencionar que este adaptador esta relacionado con el view_articulos.xml
 *Contendra un diseño por cada materia agregada en la base de datos y este adapatador nos ayudara
 *a mostrarla y tambien, poder eliminar o editar una materia seleccionada
 */

public class AdapterMaterias extends FirestoreRecyclerAdapter<Materias, AdapterMaterias.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{ //Aqui vamos a instanciar las vistas
        TextView txtvmateria;
        TextView txtvdia;
        TextView txtvnota;
        TextView txtGrupo;
        ImageButton btneditar;
        ImageButton btnEliminarMateria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtvmateria=itemView.findViewById(R.id.txtvMaterias);
            txtGrupo=itemView.findViewById(R.id.txtvGrupo);
            txtvdia=itemView.findViewById(R.id.txtvDias);
            txtvnota=itemView.findViewById(R.id.txtvNotas);
            btneditar=itemView.findViewById(R.id.btnEditar);
            btnEliminarMateria=itemView.findViewById(R.id.btnEliminarMateria);

        }
    }

    Horario_fragment activity;
    FirebaseFirestore mFirestore;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     * @param options
     * @param activity
     */
    public AdapterMaterias(@NonNull FirestoreRecyclerOptions<Materias> options, Horario_fragment activity) {
        super(options);
        this.activity = activity;
        mFirestore=FirebaseFirestore.getInstance(); //Se obtiene la instancia de la base de datos
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Materias materia) { //Para establecer los valores de cada una de las vistas
        DocumentSnapshot materiaDocument = getSnapshots().getSnapshot(holder.getAdapterPosition()); //Para obtener el id del documento
        final String id = materiaDocument.getId(); //idMateria sera donde se encuentre el documento solicitado

        //Para asignar valores en cada txtViev respectivo a cada dato
        holder.txtvmateria.setText(materia.getAsignatura());
        holder.txtGrupo.setText(String.valueOf(materia.getGrupo()));
        holder.txtvdia.setText(materia.getDias());
        holder.txtvnota.setText(materia.getNotas());
        holder.btneditar.setOnClickListener(new View.OnClickListener() { //Si click en btn editar...
            @Override
            public void onClick(View view) { //Se dirigira a Activity_EditarMateria
                Intent i = new Intent(activity.getContext(), Activity_EditarMateria.class);
                i.putExtra("articuloId", id);
                activity.startActivity(i);
            }
        });
        holder.btnEliminarMateria.setOnClickListener(new View.OnClickListener() { //Si click en btn eliminar...
            @Override
            public void onClick(View v) {
                mFirestore.collection("Materias").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { //Eliminara_todo_el_documento_de_la_base_de_datos
                        Toast.makeText(activity.getContext(), "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //Para crear cada una de las vistas
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_articulos, viewGroup, false);
        return new ViewHolder(view);
    } //Es una clase para poder instanciar las vistas que ocuparemos
}
