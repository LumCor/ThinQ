package mx.unam.ingenieria.thinq.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.thinq.Activity_Nota;
import mx.unam.ingenieria.thinq.Adaptadores.Ficha_Nota;
import mx.unam.ingenieria.thinq.Adaptadores.Notas_Adaptador;
import mx.unam.ingenieria.thinq.Adaptadores.TPendiente;
import mx.unam.ingenieria.thinq.R;

public class Notas_fragment  extends Fragment
{
    ListView lista;
    EditText txtTitulo,txtContenido;
    Notas_Adaptador notas_adaptador;
    ArrayList<Ficha_Nota> notas=new ArrayList<>();

    private static final int ADD=Menu.FIRST,DELETE=Menu.FIRST+1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notas_fragment,container,false);
        lista=view.findViewById(R.id.Lista_notas);
        //CargarNotas();
        lista.setAdapter(new Notas_Adaptador(getContext(),notas));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                notas.add(new Ficha_Nota(data.getExtras().getString("titulo"),data.getExtras().getString("contenido")));
                GuardarNotas();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void CargarNotas()
    {
        try
        {
        String aux=String.valueOf(getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM))+"/Notas.txt";
        File directorio= new File(aux);
        BufferedReader reader=new BufferedReader((new InputStreamReader(new FileInputStream(directorio))));
        int i=Integer.parseInt(reader.readLine());
        reader.close();
        Log.d("Ojo",String.valueOf(i));
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
            e.printStackTrace();
        }
        //notas.add(new Ficha_Nota("Cita Oxxo","9:30, enfrente de la veterinaria"));
    }
    private void GuardarNotas()
    {
        lista.setAdapter(new Notas_Adaptador(getContext(),notas));
        String datos=String.valueOf(notas.size())+System.getProperty("line.separator");
        for (Ficha_Nota nota:notas)
        {
            datos+=nota.getTitle()+","+nota.getTContent()+System.getProperty("line.separator");

        }
        File directorio= getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if(!directorio.exists())
            directorio.mkdir();
        try
        {
            File file = new File(directorio, "Notas" + ".txt");
            if(!file.exists())
                file.createNewFile();
            else
                {
                file.delete();
                file.createNewFile();
            }
            Log.d("Ojo",String.valueOf(file.getAbsolutePath()));
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},225);
        else
            {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("Notas.txt", getContext().MODE_PRIVATE));
                outputStreamWriter.write(datos);
                outputStreamWriter.close();
            }
        }
        catch (IOException e) { e.printStackTrace(); }

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
                startActivityForResult(intent,1);
               return true;
            case DELETE:
                notas.clear();
                lista.setAdapter(new Notas_Adaptador(getContext(),notas));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
