package mx.unam.ingenieria.thinq.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.Activity_EditTarea;
import mx.unam.ingenieria.thinq.Activity_EditTarea2;
import mx.unam.ingenieria.thinq.Fragments.Tareas_fragment;
import mx.unam.ingenieria.thinq.R;

public class TPendiente_Adaptador  extends FirestoreRecyclerAdapter<TPendiente, TPendiente_Adaptador.ViewHolder>{

    Tareas_fragment activity;
    FirebaseFirestore mFirestore;//Instancia la base

    /**
     * Declaración de cada elemento del cardview_tarea
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgvDificultad;
        TextView txtvMateria, txtvDescripcion;
        ImageButton imgbtnEditarTarea,imgbtnBorrarTarea;
        CardView cardView;//########################################

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvDificultad = itemView.findViewById(R.id.imgvDificultad);
            txtvMateria = itemView.findViewById(R.id.txtvMateria);
            txtvDescripcion = itemView.findViewById(R.id.txtvDescripcion);
            imgbtnEditarTarea = itemView.findViewById(R.id.imgbtnEditarTarea);
            imgbtnBorrarTarea = itemView.findViewById(R.id.imgbtnBorrarTarea);
            cardView = itemView.findViewById(R.id.cardView);//#####################3

        }
    }

    /**
     *Se dá la referencia del aspecto de cada celda
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_tarea, viewGroup,false);
        return new ViewHolder(v);
    }

    //Declaración de constructor
    public TPendiente_Adaptador(@NonNull FirestoreRecyclerOptions<TPendiente> options, Tareas_fragment activity){
        super(options);
        this.activity = activity;
        mFirestore= FirebaseFirestore.getInstance();

    }


    /**
     * Se vinculan los elementos del cardview con las variables de TPendiente y se habilitan los botones
     * ademas se conecta con la base de datos para obtener la localización del elemento que se quiera editar o borrar
     */
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TPendiente tarea) {
        DocumentSnapshot tareaDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = tareaDocument.getId();//Obtiene el id del elemento

        holder.txtvMateria.setText(tarea.getMateria());
        holder.txtvDescripcion.setText(tarea.getDescripcion());

        /**
         * El imageButton editar llama al Activity_EditTarea2 y envia el Id del elemento que se quiere editar
         * El imageButton elimina burra el elemento seleccionado
         */
        holder.imgbtnEditarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity.getContext(), Activity_EditTarea2.class);
                i.putExtra("articuloId", id);
                activity.startActivity(i);
            }
        });

        holder.imgbtnBorrarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirestore.collection("Tareas").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity.getContext(), "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        /**
         * Se vinculan las imagenes con la dificultad que corresponde
         * Se vincula el color del cardView segun la dificultad
         */
        if(tarea.getDificultad().equals("facil")){
            holder.imgvDificultad.setImageResource(R.drawable.facil);
            holder.cardView.setCardBackgroundColor(0xff008080);
        }
        else if(tarea.getDificultad().equals("masomenos")){
            holder.imgvDificultad.setImageResource(R.drawable.masomenos);
            holder.cardView.setCardBackgroundColor(0xffaaad4d);
        }
        else if(tarea.getDificultad().equals("dificil")){
            holder.imgvDificultad.setImageResource(R.drawable.dificil);
            holder.cardView.setCardBackgroundColor(0xFFad504d);
        }

    }

}
