package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.thinq.R;

public class Practica1_fragment extends Fragment
{
    TextView txtRes,txtRes2,txt1;
    EditText editText;
    double inicioV,finalV;
    Button bt;

    double tiempoini, tiempofin;

    private long x=1;
    private Handler manejador= new Handler(Looper.myLooper());
    long suma=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.practica1_fragment,container,false);
        txtRes=view.findViewById(R.id.txtRes);
        txtRes2=view.findViewById(R.id.txtRes2);
        txt1=view.findViewById(R.id.editTXT2);
        editText=view.findViewById(R.id.editTXT);
        bt=view.findViewById(R.id.btCalcular);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /*
               //calculo2();
               // calculo3();




                 */

                tiempoini=System.nanoTime();
                //calculo1(); Actividad 1
                //calculo2(); Actividad 2
                calculo3(); //Actividad 3
                tiempofin=(System.nanoTime()-tiempoini)/1000000000;

                txtRes2.setText(toString().valueOf(tiempofin));
            }
        });
        return view;
    }

    private void calculo1() {


        for (long i = 0; i <= 1000000000; i++) {
            suma += i;
        }
        txtRes.setText(toString().valueOf(suma));
    }

    private void calculo2()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {

                for (long i = 0; i <= 100000000; i++)
                {
                    suma += i;
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        txtRes.setText(String.valueOf(suma));
                        suma=0;
                    }
                });
            }
        }).start();

    }







    private void calculo3() {
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(long i=0; i <= 1_000_000_000;i++  )
                {
                    suma+=i;

                }
                //Log.d("Resultado","Total: " +suma);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtRes.setText( String.valueOf( suma));
                    }
                });


            }
        },6000);
    }
}
