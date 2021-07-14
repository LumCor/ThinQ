package mx.unam.ingenieria.thinq.Fragments;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import mx.unam.ingenieria.thinq.Adaptadores.Galeria_Adaptador;
import mx.unam.ingenieria.thinq.Adaptadores.ListaAudio_Adaptador;
import mx.unam.ingenieria.thinq.R;

public class Voz_fragment extends Fragment {

    private ImageButton btRecord,btStop;
    private ListView listAudios;
    private static final int Permission_toRecord=200;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.voz_fragment,container,false);

        ExisteMic();
        listAudios=(ListView) view.findViewById(R.id.ListaAudio);
        btRecord=view.findViewById(R.id.btRecordVoice);
        btStop=view.findViewById(R.id.btStopVoice);
        btStop.setEnabled(false);
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
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }

        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                btRecord.setEnabled(true);
                btStop.setEnabled(false);
                Toast.makeText(getContext(),"La grabación terminó",Toast.LENGTH_SHORT).show();
            }
        });
        
        return view;
    }
    private void ExisteMic()
    {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Permission_toRecord)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                cargarAudios();
            else
                Toast.makeText(getContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
        }
    }
    private void cargarAudios()
    {
        ArrayList<String> audios=new ArrayList<>();
        String AbsoultePathOfImage = getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() ;
        File file = new File(AbsoultePathOfImage);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            audios.add(files[i].getAbsolutePath());
        }
        //listAudios.setAdapter(new ListaAudio_Adaptador(getContext(),audios));
    }
    private String getRecordingFilePath() throws IOException {
        ContextWrapper contextWrapper=new ContextWrapper(getContext());
        File directorio=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file=File.createTempFile("audio_ThinQ_",".mp3",directorio);
        return file.getPath();
    }
}
