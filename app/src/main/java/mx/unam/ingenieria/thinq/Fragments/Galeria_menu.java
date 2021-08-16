package mx.unam.ingenieria.thinq.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.unam.ingenieria.thinq.R;

public class Galeria_menu extends Fragment {

    private BottomNavigationView bottomNavigation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.galeria_menu,container,false);
        bottomNavigation=view.findViewById(R.id.galeria_menu);
        Fragment fragment=new Galeria_fragment();
        getChildFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        return view;
    }

    /**
     * El siguiente código permite cambiar el contenido del contenedor del xml de este fragment, se mantendrá el elemento del menú inferior
     * y pondrá el fragment seleccionado
     */
    BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.btImg:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_layout, new Galeria_fragment()).commit();
                    break;
                case R.id.btVoz:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_layout, new Voz_fragment()).commit();
                    break;
                case R.id.btNotas:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_layout, new Notas_fragment()).commit();
                    break;
            }
            return true;
        }
    };
}
