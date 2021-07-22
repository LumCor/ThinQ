package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VerPDFActivity extends AppCompatActivity {

    public final static long ONE_MEGABYTE = 1024 * 1024*20; //Para crear un archivo de 20 MB

    private String libroNombre;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_p_d_f);

        libroNombre=getIntent().getStringExtra("TITULO LIBRO");
        pdfView = findViewById(R.id.pdfView);

        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance(); //Para recuperar el yitulo de libro especifico
        StorageReference mmFirebaseStorageRef = mFirebaseStorage.getReference().child("Libros");

        mmFirebaseStorageRef.child(libroNombre).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                pdfView.fromBytes(bytes).load();
            }
        }).addOnFailureListener((e) -> {
            Toast.makeText(VerPDFActivity.this, "Descarga incorrecta", Toast.LENGTH_LONG).show();
        });

    }
}