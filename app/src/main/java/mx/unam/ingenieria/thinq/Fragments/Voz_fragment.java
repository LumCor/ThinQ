package mx.unam.ingenieria.thinq.Fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mx.unam.ingenieria.thinq.Activity_Nota;
import mx.unam.ingenieria.thinq.Adaptadores.Ficha_Adaptador;
import mx.unam.ingenieria.thinq.Adaptadores.Galeria_Adaptador;
import mx.unam.ingenieria.thinq.Adaptadores.ListaAudio_Adaptador;
import mx.unam.ingenieria.thinq.Adaptadores.Notas_Adaptador;
import mx.unam.ingenieria.thinq.R;

public class Voz_fragment extends Fragment {

    private ImageButton btRecord,btStop;
    private ListView listAudios;
    private static final int Permission_toRecord=200;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private ArrayList<String> audios=new ArrayList<>();
    private static final int DELETE=Menu.FIRST;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.voz_fragment,container,false);
        listAudios=(ListView) view.findViewById(R.id.ListaAudio);
        try { ExisteMic(); } catch (IOException e) { e.printStackTrace(); }
        btRecord=view.findViewById(R.id.btRecordVoice);
        btStop=view.findViewById(R.id.btStopVoice);
        btStop.setEnabled(false);
        /**
         * El siguiente código permite empezar una grabación
         */
        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                mediaRecorder= new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(getRecordingFilePath());
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.prepare();
                mediaRecorder.start();
                Toast.makeText(getContext(),"La grabación ha comenzado",Toast.LENGTH_SHORT).show();
                btRecord.setEnabled(false);
                btStop.setEnabled(true);
                btRecord.setVisibility(View.INVISIBLE);
                btStop.setVisibility(View.VISIBLE);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }

        });
        /**
         * El siguiente código termina la grabación que este en proceso y guarda el archivo de audio
         * en el almacenamiento específico de la aplicación
         */
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                try { cargarAudios(); } catch (IOException e) { e.printStackTrace(); }
                btRecord.setEnabled(true);
                btStop.setEnabled(false);
                btRecord.setVisibility(View.VISIBLE);
                btStop.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"La grabación terminó",Toast.LENGTH_SHORT).show();
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    /**
     * El siguiente código sirve para constatar si existe un micrófono en el dispositivo.
     * En caso de que si exista un micrófono, solicita el acceso al mismo en caso de no tenerlo;
     * tras esto se cargan los datos guardados.
     * En caso de que no este disponible un micrófono, se le notifica al usuario y no permite realizar nada.
     * @throws IOException
     */
    private void ExisteMic() throws IOException {
        if(getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
        {
            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},Permission_toRecord);
            else
            {
                cargarAudios();
            }
        }
        else
            Toast.makeText(getContext(),"No se detecta micrófono",Toast.LENGTH_SHORT).show();

    }

    /**
     * En complemento al código anterior, permite cargar los datos tras obtener el permiso.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Permission_toRecord)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                try { cargarAudios(); } catch (IOException e) { e.printStackTrace(); }
            }
            else
                Toast.makeText(getContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * El siguiente código permite recuperar los archivos de audio guardados en el almacenamiento específico de la aplicación
     * @throws IOException
     */
    private void cargarAudios() throws IOException {
        audios.clear();
        ArrayList<Ficha_Adaptador> audiosdatos=new ArrayList<>();
        String AbsoultePathOfImage = getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() ;
        File file = new File(AbsoultePathOfImage);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            mediaPlayer=new MediaPlayer();
            audios.add(files[i].getAbsolutePath());
            mediaPlayer.setDataSource(audios.get(i));
            mediaPlayer.prepare();
            mediaPlayer.start();
            audiosdatos.add(new Ficha_Adaptador(files[i].getAbsolutePath().substring(files[i].getAbsolutePath().indexOf("audio_ThinQ_")),mediaPlayer.getDuration(),files[i].lastModified()));
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.d("Ojo", "Se han añadido "+String.valueOf(i+1)+" canciones");
        }
        listAudios.setAdapter(new ListaAudio_Adaptador(getContext(),audiosdatos,audios));

    }

    /**
     * El siguiente código permite crear un archivo temporal para almacenar el archivo de audio que se grabará
     * @return
     * @throws IOException
     */
    private String getRecordingFilePath() throws IOException {
        ContextWrapper contextWrapper=new ContextWrapper(getContext());
        File directorio=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file=File.createTempFile("audio_ThinQ_",".mp3",directorio);

        return file.getPath();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notas,menu);
        menu.add(2,DELETE,0,"Borrar todos los audios");

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id)
        {
            case DELETE:
                ArrayList<Ficha_Adaptador> audiosdatos=new ArrayList<>();
                String AbsoultePathOfImage = getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() ;
                File file = new File(AbsoultePathOfImage);
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++)
                    files[i].delete();
                audiosdatos.clear();
                audios.clear();
                listAudios.setAdapter(new ListaAudio_Adaptador(getContext(),audiosdatos,audios));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
