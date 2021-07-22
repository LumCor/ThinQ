package mx.unam.ingenieria.thinq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import mx.unam.ingenieria.thinq.Fragments.Estadisticas_fragment;
import mx.unam.ingenieria.thinq.Fragments.Galeria_fragment;
import mx.unam.ingenieria.thinq.Fragments.Galeria_menu;
import mx.unam.ingenieria.thinq.Fragments.Horario_fragment;
import mx.unam.ingenieria.thinq.Fragments.Libros_fragment;
import mx.unam.ingenieria.thinq.Fragments.PantallaPrincipal_fragment;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

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

            case R.id.btCerrarSecionMenu:
                auth.signOut();
                //Intent i=new Intent(MainActivity.this, Splash.class);
                //startActivity(i);
                break;




        }
        fragmentTransaction.commit();
        return false;
    }
}