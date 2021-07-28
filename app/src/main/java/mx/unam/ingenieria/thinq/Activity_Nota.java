package mx.unam.ingenieria.thinq;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Nota extends AppCompatActivity {
    Button Add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_nota);

        Add=(Button)findViewById(R.id.btAgregar);

        Bundle bundle=this.getIntent().getExtras();
        if(bundle.getString("llave").equals("add"))
            Add.setText("Add nota");
    }
}
