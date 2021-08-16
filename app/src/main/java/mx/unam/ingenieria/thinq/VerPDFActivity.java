package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Este Activity nos ayudara a visualizar un archivo PDF, nos ayudamos de una API que encontramos en
 * GitHub.
 */

public class VerPDFActivity extends AppCompatActivity {

    public final static long ONE_MEGABYTE = 1024 * 1024*20; //Para crear un archivo de 20 MB
    //*Sera el maximo peso que podra soportar

    private String libroNombre; //Esta variable la obtenemos del adapterLibros
    private PDFView pdfView; //Variable que nos pedia la API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_p_d_f);

        libroNombre=getIntent().getStringExtra("TITULO LIBRO");
        pdfView = findViewById(R.id.pdfView);

        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance(); //Para recuperar el titulo del libro especifico
        StorageReference mmFirebaseStorageRef = mFirebaseStorage.getReference().child("Libros");

        //Si se logro obtener el archivo y tuvo una conexion exitosa...
        mmFirebaseStorageRef.child(libroNombre).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                pdfView.fromBytes(bytes).load(); //Cargara el libro de la base de datos
            }
        }).addOnFailureListener((e) -> {
            Toast.makeText(VerPDFActivity.this, "Descarga incorrecta", Toast.LENGTH_LONG).show();
        });

    }
}