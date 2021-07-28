package mx.unam.ingenieria.thinq.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.R;

public class TPendiente_Adaptador  extends RecyclerView.Adapter<TPendiente_Adaptador.ViewHolder> {

    ArrayList<TPendiente> tareas;

    public TPendiente_Adaptador(Context context, ArrayList<TPendiente> list){

        tareas = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgvDificultad;
        TextView txtvMateria, txtvDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvDificultad = itemView.findViewById(R.id.imgvDificultad);
            txtvMateria = itemView.findViewById(R.id.txtvMateria);
            txtvDescripcion = itemView.findViewById(R.id.txtvDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tarea, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtvMateria.setText(tareas.get(position).getMateria());
        holder.txtvDescripcion.setText(tareas.get(position).getDescripcion());

        if(tareas.get(position).getDificultad().equals("facil")){
            holder.imgvDificultad.setImageResource(R.drawable.facil);
        }
        else if(tareas.get(position).getDificultad().equals("masomenos")){
            holder.imgvDificultad.setImageResource(R.drawable.masomenos);
        }
        else if(tareas.get(position).getDificultad().equals("dificil")){
            holder.imgvDificultad.setImageResource(R.drawable.dificil);
        }

    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }
}
