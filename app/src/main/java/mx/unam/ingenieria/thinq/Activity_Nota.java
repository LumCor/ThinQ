package mx.unam.ingenieria.thinq;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mx.unam.ingenieria.thinq.Adaptadores.Ficha_Nota;
import mx.unam.ingenieria.thinq.Fragments.Notas_fragment;

public class Activity_Nota extends AppCompatActivity {
    Button Add;
    EditText txtTitulo,txtContenido;
    String getTitle,getContent,caso;
    private static final int SALIR= Menu.FIRST;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_nota);

        Add=(Button)findViewById(R.id.btAgregar);
        txtTitulo=findViewById(R.id.txtTitulo);
        txtContenido=findViewById(R.id.txtNota);

        Bundle bundle=this.getIntent().getExtras();
        caso=bundle.getString("llave");
        getTitle=bundle.getString("titulo");
        getContent=bundle.getString("contenido");
        switch (caso)
        {
            case "add":
                Add.setText("Crear nota");
                break;
            case "edit":
                Add.setText("Actualizar");
                txtTitulo.setText(getTitle);
                txtContenido.setText(getContent);
                break;
        }
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NuevaNota();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_notas,menu);
        menu.add(1,SALIR,0,"Salir");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case SALIR:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void NuevaNota()
    {
        Log.d("ojo",txtTitulo.getText().toString());
        Log.d("ojo",txtContenido.getText().toString());
        if(txtTitulo.getText().toString().equals(""))
        {
            Toast.makeText(this, "Inserte un título", Toast.LENGTH_SHORT).show();
            txtTitulo.requestFocus();

        }
        else if(txtContenido.getText().toString().equals(""))
        {
            Toast.makeText(this, "Inserte una descripción", Toast.LENGTH_SHORT).show();
            txtContenido.requestFocus();
        }
        else
        {
            Intent intent= new Intent(this, Notas_fragment.class);
            intent.putExtra("titulo",txtTitulo.getText().toString());
            intent.putExtra("contenido",txtContenido.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
