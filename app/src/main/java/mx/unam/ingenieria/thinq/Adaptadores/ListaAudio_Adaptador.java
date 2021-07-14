package mx.unam.ingenieria.thinq.Adaptadores;

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
import java.util.List;

import mx.unam.ingenieria.thinq.R;

public class ListaAudio_Adaptador extends BaseAdapter {
    private Context context;
    public List<String> audiosArray;
    private MediaPlayer mediaPlayer;

    public ListaAudio_Adaptador(Context context, List<String> audiosArray) {
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
        if (view==null)
            view=LayoutInflater.from(context).inflate(R.layout.ficha_audio,null);
        /*
        Boolean reproduciendo=false;
        TextView nombre=view.findViewById(R.id.name_audio),
                duracion=view.findViewById(R.id.duracion_audio);
        ImageButton boton=view.findViewById(R.id.btFicha);

        if(reproduciendo==false)
        {
            boton.setImageResource(context.getResources().getIdentifier("mx.unam.ingenieria.thinq"+"@drawable/ic_baseline_play_circle_24",null,null));
        }
        else
        {
            boton.setImageResource(context.getResources().getIdentifier("mx.unam.ingenieria.thinq"+"@drawable/ic_baseline_stop_circle_24",null,null));
        }
        mediaPlayer=new MediaPlayer();
        try
        {
            mediaPlayer.setDataSource(audiosArray.get(position));
            nombre.setText(mediaPlayer.getAudioSessionId());
            duracion.setText(mediaPlayer.getDuration());
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        catch (IOException e) { e.printStackTrace(); }
*/

        return view;
    }
}
