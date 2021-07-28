package mx.unam.ingenieria.thinq.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.R;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente_Adaptador;

public class Tareas_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter myAdapter;
    ArrayList<TPendiente> tareas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tareas_fragment,container,false);
        recyclerView = view.findViewById(R.id.listPendientes);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        tareas = new ArrayList<>();
        tareas.add(new TPendiente("Matematica", "Serie", "facil"));
        tareas.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tareas.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));
        tareas.add(new TPendiente("Matematica", "Serie", "facil"));
        tareas.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tareas.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));
        tareas.add(new TPendiente("Matematica", "Serie", "facil"));
        tareas.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tareas.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));
        tareas.add(new TPendiente("Matematica", "Serie", "facil"));
        tareas.add(new TPendiente("Ecuaciones", "Investigacion", "masomenos"));
        tareas.add(new TPendiente("Programacion", "ProyectoAlv :'v", "dificil"));

        myAdapter = new TPendiente_Adaptador(getContext(), tareas);
        recyclerView.setAdapter(myAdapter);
        return view;
    }
}
