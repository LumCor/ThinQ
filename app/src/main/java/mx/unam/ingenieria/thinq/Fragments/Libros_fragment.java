package mx.unam.ingenieria.thinq.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.R;
import mx.unam.ingenieria.thinq.VerPDFActivity;

public class Libros_fragment extends Fragment {

    private ListView listViewLibros;
    private ArrayList<String> libros;
    private StorageReference storageLibros; //Para traer la referencia de la base de datos

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.libros_fragment,container,false);

        listViewLibros=view.findViewById(R.id.listviewLibros);
        libros= new ArrayList<>();

        //Inicializamos el storage
        storageLibros = FirebaseStorage.getInstance().getReference();

        //Traemos la referencia donde se tiene guardado los libros
        StorageReference ref =storageLibros.child("Libros");

        //Solicitamos los titulos de los libros
        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() { //Si se recuperan los libros satisfactoriamente
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference item: listResult.getItems()){
                    libros.add(item.getName()+""); //Mete los libros en el arrayList
                }
                //Adaptador
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, libros);

                listViewLibros.setAdapter(adapter);
                listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String titulo_seleccionado = listViewLibros.getItemAtPosition(position).toString();

                        //abir en la activity elñ libro
                        Intent i= new Intent(view.getContext(), VerPDFActivity.class);
                        i.putExtra("TITULO LIBRO", titulo_seleccionado);
                        startActivity(i);

                    }
                });


            }
        }).addOnFailureListener((e) -> { //Una excepecion
            AlertDialog.Builder builder1= new AlertDialog.Builder(view.getContext());
            builder1.setMessage("Ocurrio un error al cargar los libros. Revisa la conexion de internet y vuelva a itentarlo");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert1= builder1.create();
            alert1.show();
        });





        return view;

    }
}
