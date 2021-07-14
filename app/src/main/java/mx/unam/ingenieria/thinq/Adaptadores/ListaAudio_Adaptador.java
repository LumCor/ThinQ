package mx.unam.ingenieria.thinq.Adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.thinq.R;

public class ListaAudio_Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Ficha_Adaptador> audiosArray;

    public ListaAudio_Adaptador(Context context, ArrayList<Ficha_Adaptador> audiosArray) {
        this.context = context;
        this.audiosArray = audiosArray;
    }

    @Override
    public int getCount() {
        if(audiosArray!=null)
            return audiosArray.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {return audiosArray.get(position);    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view=LayoutInflater.from(context).inflate(R.layout.ficha_audio,null);
        Ficha_Adaptador ficha=(Ficha_Adaptador)getItem(position);
        TextView nombre=view.findViewById(R.id.name_audio),
                duracion=view.findViewById(R.id.duracion_audio);
                //fecha=view.findViewById(R.id.date_audio);
        ImageButton boton=view.findViewById(R.id.btFicha);
        nombre.setText(ficha.getName());
        float tiempo= ficha.getDuracion()/1000;
        int min, seg, mseg;
        min=(int)tiempo/60;
        seg=(int)(tiempo-min*60);
        mseg=(int)(tiempo-min*60-seg)*10;
        String cadena;
        if((int)tiempo/60<9)
            cadena="0"+String.valueOf(min)+":";
        else
            cadena=String.valueOf(min);
        if((int)(tiempo-min*60)<9)
            cadena=cadena+"0"+String.valueOf(seg)+":";
        else
            cadena=cadena+String.valueOf(seg)+":";
        if((int)(tiempo-min*60-seg)*10<9)
            cadena=cadena+"0"+String.valueOf(mseg);
        else
            cadena=cadena+String.valueOf(mseg);
        duracion.setText(cadena);
        /*
        Boolean reproduciendo=false;
        if(reproduciendo==false)
        {
            boton.setImageResource(context.getResources().getIdentifier("mx.unam.ingenieria.thinq"+"@drawable/ic_baseline_play_circle_24",null,null));
        }
        else
        {
            boton.setImageResource(context.getResources().getIdentifier("mx.unam.ingenieria.thinq"+"@drawable/ic_baseline_stop_circle_24",null,null));
        }
*/

        return view;
    }
}
