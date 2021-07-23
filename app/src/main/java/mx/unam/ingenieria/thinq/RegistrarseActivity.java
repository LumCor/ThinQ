package mx.unam.ingenieria.thinq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText verificaContrasena;
    private EditText nombre;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        correo=findViewById(R.id.txtCorreo_Regis);
        contrasena=findViewById(R.id.txtContrasema_Regis);
        verificaContrasena=findViewById(R.id.txtConfirmaContra_Regis);
        nombre=findViewById(R.id.txtNombre_Regis);

        mAuth = FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
    }


    @Override
    public void onStart() { //Metodo para registrar nuevos usuarios
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    public void  registrarUsuario(View view) {

        if (!contrasena.getText().toString().isEmpty() && !verificaContrasena.getText().toString().isEmpty()
                && !correo.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty()) {
            if (contrasena.getText().toString().equals(verificaContrasena.getText().toString())) {
                if (contrasena.length() >= 6) {


                    mAuth.createUserWithEmailAndPassword(correo.getText().toString().trim(), contrasena.getText().toString().trim())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(), "Usuario creado!", Toast.LENGTH_SHORT).show();

                                        Map<String, Object> map = new HashMap<>(); //Un mapa que contrendra todas nuestras variables
                                        map.put("Horario", null);
                                        map.put("Tareas", null);

                                        //mFirestore.collection("Usuario").document().set();
                                        mFirestore.collection("Usuarios").document(nombre.getText().toString()).set(map);
                                        //updateUI(user);
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        //Si el registro no es exitoso
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }


                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(getApplicationContext(), "Las contraseñas no coiciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}