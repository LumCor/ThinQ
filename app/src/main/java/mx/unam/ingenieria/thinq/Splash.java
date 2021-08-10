package mx.unam.ingenieria.thinq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {
    //public static final String USUARIOLOGIN ="usuarioLogin";
    FirebaseAuth auth;
    //boolean usuarioLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth=FirebaseAuth.getInstance();
        //usuarioLogin=false;


        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent intent;

                if (auth.getCurrentUser()==null ){ //|| usuarioLogin==false

                    intent = new Intent(Splash.this, LoginActivity.class);
                    //usuarioLogin=false;
                }
                else {

                    intent=new Intent(Splash.this,MainActivity.class);
                    //intent.putExtra(USUARIOLOGIN,usuarioLogin);
                    //usuarioLogin=false;
                }

                startActivity(intent);
                finish();
            }
        };
        Timer timer= new Timer();
        timer.schedule(tarea,3000);
    }
}