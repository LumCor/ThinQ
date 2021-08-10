package mx.unam.ingenieria.thinq.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.R;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente_Adaptador;

public class Tareas_fragment extends Fragment/*implements TPendiente_Adaptador.ItemSelected*/ {


    //EditarTarea_fragment editarTarea_fragment;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter myAdapter;
    ArrayList<TPendiente> tarea;
    Button btnNuevaTarea;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tareas_fragment,container,false);
        btnNuevaTarea = view.findViewById(R.id.btnNuevaTarea);
        recyclerView = view.findViewById(R.id.listPendientes);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        tarea = new ArrayList<>();
        tarea.add(new TPendiente("Matematica", "Serie", "facil"));
        tarea.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tarea.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));
        tarea.add(new TPendiente("Matematica", "Serie", "facil"));
        tarea.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tarea.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));
        //btnNuevaTarea.setOnClickListener(onClickEditar);

        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tarea.add(new TPendiente("Materia", "Descripcion","dificil"));
                myAdapter.notifyDataSetChanged();

            }
        });

        myAdapter = new TPendiente_Adaptador(getContext(), tarea);
        recyclerView.setAdapter(myAdapter);
        return view;
    }

    /*View.OnClickListener onClickEditar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editarTarea_fragment= new EditarTarea_fragment();
            getChildFragmentManager().beginTransaction().replace(R.id.editarTarea_fragment).commit();
        }
    };*/

    /*@Override
    public void onItemClicked(int index) {
        Toast.makeText( getContext() , tarea.get(index).getMateria(), Toast.LENGTH_SHORT).show();

    }*/
}
