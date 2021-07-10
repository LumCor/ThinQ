package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Result;

import mx.unam.ingenieria.thinq.R;

import static android.app.Activity.RESULT_OK;

public class Galeria_fragment extends Fragment {
    private ImageView imageView;
    private ImageButton btCamara;
    private String ruta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.galeria_fragment,container,false);
        btCamara=view.findViewById(R.id.btCamara);
        imageView=view.findViewById(R.id.imageV);
        btCamara.setOnClickListener(v -> abrirCamara());
        return view;
    }
    private void abrirCamara()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if(intent.resolveActivity(getActivity().getPackageManager()) !=null)
        {
            File img=null;
            try{ img=crearImg(); }
            catch (IOException error) { Log.e("Error",error.toString()); }
            if(img!=null)
            {
                Uri fotoUri= FileProvider.getUriForFile(getContext(),"mx.unam.ingenieria.thinq.fileprovider",img);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent,1);
            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== RESULT_OK)
        {
            Bitmap imgBitmap= BitmapFactory.decodeFile(ruta);
            imageView.setImageBitmap(imgBitmap);
        }
    }
    private File crearImg() throws IOException {
        String name="foto_ThinQ_";
        File directorio= getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img=File.createTempFile(name,".jpg",directorio);
        ruta=img.getAbsolutePath();
        return img;
    }
}
