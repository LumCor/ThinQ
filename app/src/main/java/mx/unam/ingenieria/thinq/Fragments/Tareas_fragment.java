package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.Activity_CrearHorario;
import mx.unam.ingenieria.thinq.Activity_EditTarea;
import mx.unam.ingenieria.thinq.Activity_Nota;
import mx.unam.ingenieria.thinq.Adaptadores.AdapterMaterias;
import mx.unam.ingenieria.thinq.Adaptadores.Materias;
import mx.unam.ingenieria.thinq.Adaptadores.Notas_Adaptador;
import mx.unam.ingenieria.thinq.R;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente_Adaptador;

public class Tareas_fragment extends Fragment {


    private Button btnNuevaTarea;
    RecyclerView recyclerViewTareas; //la listas que contendrá los cardview
    TPendiente_Adaptador myAdapter;
    FirebaseFirestore myFirestore;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tareas_fragment,container,false);

        btnNuevaTarea = view.findViewById(R.id.btnNuevaTarea);
        recyclerViewTareas = view.findViewById(R.id.listPendientes);

        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myFirestore = FirebaseFirestore.getInstance();

        Query query = myFirestore.collection("Tareas");

        FirestoreRecyclerOptions<TPendiente> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<TPendiente>()
                .setQuery(query, TPendiente.class).build();


        myAdapter = new TPendiente_Adaptador(firestoreRecyclerOptions, this);
        myAdapter.notifyDataSetChanged(); //Por si hay cambios instantaneos
        recyclerViewTareas.setAdapter(myAdapter);

        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), Activity_EditTarea.class));
            }
        });

        return view;
    }

    @Override
    public void onStart() { //Si el usuario esta dentro de la app realice cambios
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
