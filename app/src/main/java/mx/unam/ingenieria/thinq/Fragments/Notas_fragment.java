package mx.unam.ingenieria.thinq.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import mx.unam.ingenieria.thinq.Activity_Nota;
import mx.unam.ingenieria.thinq.Adaptadores.Notas_Adaptador;
import mx.unam.ingenieria.thinq.R;

public class Notas_fragment  extends Fragment
{
    ListView lista;
    EditText txtTitulo,txtContenido;
    Notas_Adaptador notas_adaptador;
    List<String> item=null;
    private static final int ADD=Menu.FIRST,DELETE=Menu.FIRST+1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notas_fragment,container,false);
        lista=view.findViewById(R.id.Lista_notas);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notas,menu);
        menu.add(1,ADD,0,"Crear nota");
        menu.add(2,DELETE,0,"Borrar todas las notas");
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case ADD:
                Intent intent= new Intent(getContext(), Activity_Nota.class);
                intent.putExtra("llave","add");
                startActivity(intent);
               return true;
            case DELETE:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
