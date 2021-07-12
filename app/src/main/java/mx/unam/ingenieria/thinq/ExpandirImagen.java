package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.thinq.Adaptadores.Galeria_Adaptador;
import mx.unam.ingenieria.thinq.Fragments.Imagenes;

public class ExpandirImagen extends AppCompatActivity {
    ImageView imageView;
    Galeria_Adaptador galeriaAdaptador;
    List<String> imagenes;
    List<Bitmap> bits=new ArrayList<Bitmap>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandir_imagen);
        imageView=(ImageView)findViewById(R.id.iv_completa);
        Intent intent=getIntent();
        int posicion=intent.getExtras().getInt("Fotos_ThinQ");
        imagenes=Imagenes.Imagenes(this);
        for(int i=0;i<imagenes.size();i++)
        {
            bits.add(BitmapFactory.decodeFile(imagenes.get(i)));
        }
        galeriaAdaptador=new Galeria_Adaptador(this, bits);
        imageView.setImageBitmap(galeriaAdaptador.imageArray.get(posicion));

    }
}