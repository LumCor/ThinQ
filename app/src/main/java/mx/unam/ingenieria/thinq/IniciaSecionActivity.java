package mx.unam.ingenieria.thinq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciaSecionActivity extends AppCompatActivity {

    private TextView correo;
    private  TextView contrasena;
    private FirebaseAuth mAuth;
    //private Button btnIniciaSecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia_secion);
        correo =findViewById(R.id.txtCorreo_IniciaSec);
        contrasena =findViewById(R.id.txtContrasena_IniciaSec);
        mAuth = FirebaseAuth.getInstance();
        //btnIniciaSecion=findViewById(R.id.btnIniciaSecion);

    }

    public  void  iniciarSecion(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString().trim(), contrasena.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Autentificacion exitosa", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}