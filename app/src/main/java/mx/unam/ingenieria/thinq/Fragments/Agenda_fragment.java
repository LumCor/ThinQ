package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.thinq.Activity_CrearHorario;
import mx.unam.ingenieria.thinq.AgregarEventosActivity;
import mx.unam.ingenieria.thinq.R;
import mx.unam.ingenieria.thinq.VerEventosActivity;

public class Agenda_fragment extends Fragment implements CalendarView.OnDateChangeListener{

    private CalendarView calendarView;

    private Button btnVer;
    private Button btnAgregar;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.agenda_fragment,container,false);

        calendarView = view.findViewById(R.id.CalendarView); //referenciamos al calendario que está en la vista
        calendarView.setOnDateChangeListener(this);

        btnVer = (Button) view.findViewById(R.id.btnVer);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);

        return view;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView CalendarView, int year, int month, int dayOfMonth) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        final int dia, mes, anio;
        dia = dayOfMonth;
        mes = month + 1;
        anio = year;

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(v.getContext(), AgregarEventosActivity.class));

                Intent intent = new Intent(v.getContext(), AgregarEventosActivity.class);

                //le pasaremos los datos a la nueva actividad
                Bundle bundle = new Bundle();
                bundle.putInt("dia", dia);
                bundle.putInt("mes", mes);
                bundle.putInt("anio", anio);

                //agregaremos el objeto de tipo Bundle al intent
                intent.putExtras(bundle);

                //desplegamos la actividad
                startActivity(intent);
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(v.getContext(), VerEventosActivity.class));
                Intent intent = new Intent(v.getContext(), VerEventosActivity.class);

                //le pasaremos los datos a la nueva actividad
                Bundle bundle = new Bundle();
                bundle.putInt("dia", dia);
                bundle.putInt("mes", mes);
                bundle.putInt("anio", anio);

                //agregaremos el objeto de tipo Bundle al intent
                intent.putExtras(bundle);

                //desplegamos la actividad
                startActivity(intent);

            }
        });
    }


}
