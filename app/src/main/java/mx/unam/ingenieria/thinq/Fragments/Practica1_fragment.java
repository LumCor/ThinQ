package mx.unam.ingenieria.thinq.Fragments;

import android.content.Intent;
import android.os.Bundle;
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
    Button bt;
    private long x=1;
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
                try {
                    calculo();
                    calculo2();
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        return view;
    }
    private void calculo()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                String K= editText.getText().toString();
                int k=Integer.parseInt(K);
                txt1.setText(String.valueOf(k+1));
                for (long j=0;j<k;j++)
                {
                    for (long i = 0; i <= 100000000; i++)
                    {
                        x += i;
                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        txtRes.setText(String.valueOf(x));
                        x=0;
                    }
                });
            }
        }).start();

    }

    private void calculo2() throws InterruptedException
    {

        /*
        for(long i=0;i<=10;i++)
        {
            txtCont.setText(String.valueOf(i));
        }
        */

    }
}
