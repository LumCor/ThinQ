package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.internal.StorageReferenceUri;

import java.io.FileNotFoundException;
import java.io.IOException;

import mx.unam.ingenieria.thinq.R;

import static android.app.Activity.RESULT_OK;

public class Estadisticas_fragment extends Fragment {
    private static final int PICK_FOTO = 1;
    private Button uploadBtn;
    private StorageReference mStorage;

    private static final int GALLERY_INTENT=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.estadisticas_fragment,container,false);

        mStorage= FirebaseStorage.getInstance().getReference();
        uploadBtn=view.findViewById(R.id.btnSubir);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent();
                //Seleccionando tipo de archivo por abrir
                intentGaleria.setType("image/*");
                //Seleccionar la aplicación que abrirá el tipo de archivo
                intentGaleria.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intentGaleria, "Selecciona una imagen"), PICK_FOTO);
            }
        });



        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_FOTO && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            Bitmap bitmap=null;
            Uri directorio= data.getData();

            final StorageReference directorior=mStorage.child("fotosX").child(directorio.getLastPathSegment());
            if (Build.VERSION.SDK_INT>=28){
                ImageDecoder.Source source= ImageDecoder.createSource(getActivity().getContentResolver(),directorio);
                try {
                    bitmap = ImageDecoder.decodeBitmap(source);
                    //imgvFoto.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),directorio);
                    //imgvFoto.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(getContext(),"AHI VA"+bitmap.toString(),Toast.LENGTH_SHORT).show();

            directorior.putFile(directorio).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        Toast.makeText(getContext(),"No se pudo subir el archivo en el storage",Toast.LENGTH_SHORT).show();
                        throw new Exception();
                    }
                    return directorior.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(),"Si se pudo subir el archivo en el storage",Toast.LENGTH_SHORT).show();
                        Uri downloadLink=task.getResult();
                       // imagen=downloadLink.toString();
                        //Log.d("Link","Link de descarga: "+ imagen);
                    }
                }
            });



        }
    }


}
