package mx.unam.ingenieria.thinq.Adaptadores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.R;

public class Notas_Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Ficha_Nota> notas;
    public Notas_Adaptador(Context context, ArrayList<Ficha_Nota> notas)
    {
        this.context=context;
        this.notas=notas;
    }

    @Override
    public int getCount() {
        if(notas!=null)
            return notas.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return  notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Ficha_Nota ficha=(Ficha_Nota)getItem(position);
        TextView textView=new TextView(context);
        textView.setText(ficha.getTitle()+"\n"+ficha.getTContent());
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }
}
