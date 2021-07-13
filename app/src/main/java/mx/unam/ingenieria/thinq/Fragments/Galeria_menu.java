package mx.unam.ingenieria.thinq.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import mx.unam.ingenieria.thinq.R;

public class Galeria_menu extends Fragment {

    MeowBottomNavigation bottomNavigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.galeria_menu,container,false);
        bottomNavigation=view.findViewById(R.id.galeria_menu);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_image_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_keyboard_voice_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_note_24));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item)
            {
                Fragment fragment=null;
                switch (item.getId())
                {
                    case 1:
                        fragment=new Galeria_fragment();
                        break;
                    case 2:
                        fragment=new Voz_fragment();
                        break;
                    case 3:
                        fragment=new Notas_fragment();
                        break;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

            }
        });
        return view;
    }
}
