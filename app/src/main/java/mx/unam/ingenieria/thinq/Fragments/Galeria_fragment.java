package mx.unam.ingenieria.thinq.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import mx.unam.ingenieria.thinq.Adaptadores.Galeria_Adaptador;
import mx.unam.ingenieria.thinq.ExpandirImagen;
import mx.unam.ingenieria.thinq.R;

import static android.app.Activity.RESULT_OK;

public class Galeria_fragment extends Fragment {

    private ImageView imageView;
    private ImageButton btCamara;
    private String ruta;
    private GridView gridView;
    private Galeria_Adaptador galeria_adaptador;
    private ProgressBar progressBar;
    List<String> imagenes;
    List<Bitmap> bits=new ArrayList<Bitmap> ();

    private static final int Permission_toRead_Code=101;

    private StorageReference mStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.galeria_fragment,container,false);
        btCamara=view.findViewById(R.id.btCamara);
        imageView=view.findViewById(R.id.imageV);
        btCamara.setOnClickListener(v -> abrirCamara());
        gridView=view.findViewById(R.id.gvGaleria);
        progressBar=view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        /**
         * El siguiente código permite seleccionar alguna imagén de la galería y expandirla
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), ExpandirImagen.class);
                intent.putExtra("Fotos_ThinQ",position);
                startActivity(intent);
            }
        });
        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Permission_toRead_Code);
        else
            cargarImg();

        registerForContextMenu(gridView);//hace el gesto de mantener presionado
        mStorage= FirebaseStorage.getInstance().getReference();//creando una referencia de Firebase

        return view;
    }

    /**
     * El siguiente código permite recuperar las imagenes guardadas del almacenamiento específico de
     * la aplicación si y solo si se tienen los permisos de acceso al almacenamiento.
     * De lo contrario no se podrá interactuar con la cámara ni la galería
     * @param requestCode Código que concede el permiso de lectura del almacenamiento
     * @param permissions  Código de permiso a comparar si se concedio el acceso
     * @param grantResults Permisión concedida
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Permission_toRead_Code)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                cargarImg();
            else
                Toast.makeText(getContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * El siguiente código permite obtener un arreglo de los bits de las imágenes guardadas del dispositivo
     * de tal manera que dichos bits se manden al adaptador quién colocará las imagenes en la galería
     */
    private void cargarImg()
    {
        imagenes=Imagenes.Imagenes(getContext());
        for(int i=0;i<imagenes.size();i++)
                {
                    bits.add(BitmapFactory.decodeFile(imagenes.get(i)));
                    Log.d("Ojo2","Se cargo una imagen");
                }
        gridView.setAdapter(new Galeria_Adaptador(getContext(),bits));
    }

    /**
     * Este código permite accesar a la cámara y obtener una fotografía.
     *
     */
    private void abrirCamara()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getActivity().getPackageManager()) !=null)
        {
            File img=null;
            try{ img=crearImg(); }
            catch (IOException error) { Log.e("Error",error.toString()); }
            if(img!=null)
            {
                Uri fotoUri= FileProvider.getUriForFile(getContext(),"mx.unam.ingenieria.thinq.fileprovider",img);
                Log.d("Ojo2",String.valueOf(fotoUri));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent,1);
            }

        }

    }

    /**
     * Este código permite crear un archivo fotográfico a partir de los datos proporcionados por la
     * cámara y guarda la fotografía en el almacenamiento específico
     * @return
     * @throws IOException
     */
    private File crearImg() throws IOException {
        String name="foto_ThinQ_";
        File directorio= getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img=File.createTempFile(name,".jpg",directorio);
        ruta=img.getAbsolutePath();
        Log.d("Ojo3",ruta);
        return img;
    }
    @Override
    /**
     * El código permite ejecutar una visualización de la fotografía obtenida en un contenedor del xml,
     * así mismo se realiza una actualización dl gridview(galería)
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== RESULT_OK)
        {
            Bitmap imgBitmap= BitmapFactory.decodeFile(ruta);
            imageView.setImageBitmap(imgBitmap);

        }
    }


    //cargar imagen a base de datos


    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();//vamos a inflar un menu
        inflater.inflate(R.menu.menu_contextual_images,menu);//el menu que vamos a inflar

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//indica el elemento seleccionado


        switch (item.getItemId()){
            case R.id.menContImaSubir:
                Subir(info.position);//se pasa como parametro la posicion de la imagen dentro del gridview

                break;
            case R.id.menContImaEliminar:
                File file=new File(imagenes.get(info.position));
                file.delete();
                bits.remove(info.position);
                imagenes.remove(info.position);
                gridView.setAdapter(new Galeria_Adaptador(getContext(),bits));
                Toast.makeText(getContext(),"eliminando archivo",Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    private void Subir(int poss) {
        Uri uri = FileProvider.getUriForFile(getContext(),"mx.unam.ingenieria.thinq.fileprovider",new File(imagenes.get(poss)));//Se almacena la imagen seleccionada con poss en un uri
        final StorageReference filePath=mStorage.child("MisFotos").child(imagenes.get(poss));//Se hace referencia a la carpeta de almacenamiento
        progressBar.setVisibility(View.VISIBLE);//haciendo visible el widget progressbar
        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);//haciendo invisible el widget progressbar
                Toast.makeText(getContext(),"Se guardó exitosamente la imagen",Toast.LENGTH_SHORT).show();
            }
        });

    }



}

