package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import mx.unam.ingenieria.thinq.Activity_CrearHorario;
import mx.unam.ingenieria.thinq.Adaptadores.AdapterMaterias;
import mx.unam.ingenieria.thinq.Adaptadores.Materias;
import mx.unam.ingenieria.thinq.IniciaSecionActivity;
import mx.unam.ingenieria.thinq.LoginActivity;
import mx.unam.ingenieria.thinq.R;

public class Horario_fragment extends Fragment {

    private Button btnAgregarMateriaH;
    RecyclerView recyclerViewHorario;
    AdapterMaterias mAdapater;
    FirebaseFirestore mFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horario_fragment, container, false);

        btnAgregarMateriaH = view.findViewById(R.id.btnAgregarMateriaH);
        recyclerViewHorario = view.findViewById(R.id.recyclerHorario);
        recyclerViewHorario.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mFirestore = FirebaseFirestore.getInstance();

        Query query = mFirestore.collection("Materias");

        FirestoreRecyclerOptions<Materias> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Materias>()
                .setQuery(query, Materias.class).build();

        mAdapater = new AdapterMaterias(firestoreRecyclerOptions, this);
        mAdapater.notifyDataSetChanged(); //Por si hay cambios instantaneos
        recyclerViewHorario.setAdapter(mAdapater);

        btnAgregarMateriaH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //xdxxdHolaaa
                startActivity(new Intent(view.getContext(), Activity_CrearHorario.class));

            }

        });


        return view;
    }


    @Override
    public void onStart() { //Si el usuario esta dentro de la app realice cambios
        super.onStart();
        mAdapater.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();


    }




}
