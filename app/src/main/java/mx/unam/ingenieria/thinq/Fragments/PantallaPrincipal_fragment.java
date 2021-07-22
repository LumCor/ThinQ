package mx.unam.ingenieria.thinq.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import mx.unam.ingenieria.thinq.R;

public class PantallaPrincipal_fragment extends Fragment {

    private Button btnCerrarSecionP;
    FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.perfil_fragment,container,false);

        btnCerrarSecionP=view.findViewById(R.id.btnCerrarSecionP);
        auth=FirebaseAuth.getInstance();



        return view;





    }


}
