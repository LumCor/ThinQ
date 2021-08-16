package mx.unam.ingenieria.thinq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import mx.unam.ingenieria.thinq.Fragments.Agenda_fragment;
import mx.unam.ingenieria.thinq.Fragments.Estadisticas_fragment;
import mx.unam.ingenieria.thinq.Fragments.Galeria_fragment;
import mx.unam.ingenieria.thinq.Fragments.Galeria_menu;
import mx.unam.ingenieria.thinq.Fragments.Horario_fragment;
import mx.unam.ingenieria.thinq.Fragments.Libros_fragment;
import mx.unam.ingenieria.thinq.Fragments.PantallaPrincipal_fragment;
import mx.unam.ingenieria.thinq.Fragments.Practica1_fragment;
import mx.unam.ingenieria.thinq.Fragments.Tareas_fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //fragments
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private PantallaPrincipal_fragment pantallaPrincipal_fragment;
    private Tareas_fragment tareas_fragment;
    private Galeria_menu galeria_menu;
    private Galeria_fragment galeria_fragment;
    private Estadisticas_fragment estadisticas_fragment;
    private Horario_fragment horario_fragment;
    private Libros_fragment libros_fragment;
    private Practica1_fragment practica1_fragment;
    private Agenda_fragment agenda_fragment;

    private ProgressDialog TempDialog;
    private CountDownTimer countDownTimer;
    private int cont =0;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pantallaPrincipal_fragment=new PantallaPrincipal_fragment();
        tareas_fragment=new Tareas_fragment();
        galeria_menu=new Galeria_menu();
        galeria_fragment=new Galeria_fragment();
        estadisticas_fragment=new Estadisticas_fragment();
        horario_fragment=new Horario_fragment();
        libros_fragment=new Libros_fragment();
        practica1_fragment= new Practica1_fragment();
        agenda_fragment = new Agenda_fragment();

        auth=FirebaseAuth.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        ///Cargar fragments
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, pantallaPrincipal_fragment);
        fragmentTransaction.commit();

    }

    /**
     * El siguiente código permite cambiar el contenido del contenedor del xml, permitiendo alojar en su interior
     * a los diferentes fragments según el botón pulsado
     * @param item Elemento identificador del botón que permite asociar un fragment
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        //Intent intent =getIntent(); //Mos devuelve el intente del splash
        //boolean usuarioLogin = intent.getBooleanExtra(Splash.USUARIOLOGIN, true); //Para generar la variable

        switch (item.getItemId())
        {
            case R.id.btPerfil:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, pantallaPrincipal_fragment);
                break;
            case R.id.btTareas:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, tareas_fragment);
                break;
            case R.id.btGaleria:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,galeria_menu);
                break;
            case R.id.btEstadisticas:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,estadisticas_fragment);
                break;

            case R.id.btHorario:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,horario_fragment);
                break;

            case R.id.btLibro:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,libros_fragment);
                break;

            case R.id.Practica1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,practica1_fragment);
                break;
            case R.id.btAgenda:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,agenda_fragment);
                break;

            case R.id.btCerrarSecionMenu:

                Intent i = new Intent(MainActivity.this, Splash.class);

                auth.signOut();
                tiempoEspera();
                startActivity(i);
                finish();

                //System.exit(0);

                break;

        }
        fragmentTransaction.commit();
        return false;
    }


    public void tiempoEspera() {


        TempDialog = new ProgressDialog(this);
        TempDialog.setMessage("Espere un momento");
        TempDialog.setCancelable(false);
        TempDialog.setProgress(cont);
        TempDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        TempDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        TempDialog.show();

        countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TempDialog.setMessage("Por favor Espere");
            }

            @Override
            public void onFinish() {
                TempDialog.dismiss();


            }
        };
    }

}

