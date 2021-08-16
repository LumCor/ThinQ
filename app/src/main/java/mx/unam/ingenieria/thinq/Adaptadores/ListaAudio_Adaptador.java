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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mx.unam.ingenieria.thinq.R;

public class ListaAudio_Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Ficha_Adaptador> audiosArray;
    private ArrayList<String> audios;
    private MediaPlayer mediaPlayer;

    public ListaAudio_Adaptador(Context context, ArrayList<Ficha_Adaptador> audiosArray, ArrayList<String> audios) {
        this.context = context;
        this.audiosArray = audiosArray;
        this.audios=audios;
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
        /**
         * Recepción de los datos del archivo de audio
         */
        TextView nombre=view.findViewById(R.id.name_audio),
                duracion=view.findViewById(R.id.duracion_audio),
                fecha=view.findViewById(R.id.date_audio);
        /**
         * se guarda el nombre en el item de la lista
         */
        nombre.setText(ficha.getName());
        float tiempo= ficha.getDuracion()/1000;
        int min, seg, mseg;
        min=(int)tiempo/60;
        seg=(int)(tiempo-min*60);
        mseg=(int)(tiempo-min*60-seg)*10;
        String cadena;
        if((int)tiempo/60<10)
            cadena="0"+String.valueOf(min)+":";
        else
            cadena=String.valueOf(min);
        if((int)(tiempo-min*60)<10)
            cadena=cadena+"0"+String.valueOf(seg)+":";
        else
            cadena=cadena+String.valueOf(seg)+":";
        if((int)(tiempo-min*60-seg)*10<10)
            cadena=cadena+"0"+String.valueOf(mseg);
        else
            cadena=cadena+String.valueOf(mseg);
        /**
         * Tras obtener la duración y hacer operaciones para transformar el tiempo en minutos y segundos
         * se guarda su duración el item de la lista
         */
        duracion.setText(cadena);
        Date date=new Date(ficha.getFecha());
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        if(calendar.get(Calendar.DATE)<10)
            cadena="0"+String.valueOf(calendar.get(Calendar.DATE))+"/";
        else
            cadena=String.valueOf(calendar.get(Calendar.DATE))+"/";
        if(calendar.get(Calendar.MONTH)<10)
            cadena=cadena+"0"+String.valueOf(calendar.get(Calendar.MONTH))+"/";
        else
            cadena=cadena+String.valueOf(calendar.get(Calendar.MONTH))+"/";
        cadena=cadena+String.valueOf(calendar.get(Calendar.YEAR));
        /**
         * Tras obtener la fecha en la cuál fue creado el archivo de audio, se transforma en un formato
         * días/mes/año y se guarda en el item de la lista
         */
        fecha.setText(cadena);
        final Boolean[] repro = {false};
        ImageButton boton=view.findViewById(R.id.btFicha);
        /**
         * Se le coloca un botón al item de la lista, dicho botón al ser presionado permite reproducir
         * el archivo de audio y detenerlo si se requiere
         */
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if(repro[0] ==false)
                    {
                        mediaPlayer=new MediaPlayer();
                        mediaPlayer.setDataSource(audios.get(position));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        boton.setImageResource(R.drawable.ic_baseline_stop_circle_24);
                        repro[0] =true;
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp)
                            {
                                repro[0]=false;
                                boton.setImageResource(R.drawable.ic_baseline_play_circle_24);
                                mediaPlayer.release();
                            }
                        });
                    }
                    else
                    {
                        mediaPlayer.stop();
                        repro[0] =false;
                        boton.setImageResource(R.drawable.ic_baseline_play_circle_24);
                    }

                } catch (IOException e) {e.printStackTrace(); }
            }
        });
        return view;
    }
}
