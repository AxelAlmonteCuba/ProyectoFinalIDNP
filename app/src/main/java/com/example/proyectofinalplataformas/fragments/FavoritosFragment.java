package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinalplataformas.Adapters.AdaptadorFavoritos;
import com.example.proyectofinalplataformas.Adapters.AdaptadorPinturas;
import com.example.proyectofinalplataformas.Entitys.FavoritosVo;
import com.example.proyectofinalplataformas.Entitys.PinturasVo;
import com.example.proyectofinalplataformas.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerFavoritos;
    ArrayList<FavoritosVo> listaFavoritos;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritosFragment newInstance(String param1, String param2) {
        FavoritosFragment fragment = new FavoritosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View vista=inflater.inflate(R.layout.fragment_favoritos, container, false);
        listaFavoritos=new ArrayList<>();
        recyclerFavoritos=vista.findViewById(R.id.recyclerId2);
        recyclerFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();

        AdaptadorFavoritos adaptador= new AdaptadorFavoritos(listaFavoritos);
        recyclerFavoritos.setAdapter(adaptador);


        return vista;

    }

    private void llenarLista() {

        listaFavoritos.add(new FavoritosVo("La Gioconda","Davinci",R.drawable.uno,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("Noche estrellada","Axelito",R.drawable.dos,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("La ultima cena","Feorella",R.drawable.tres,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("La gran ola","Raulin",R.drawable.cuatro,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("Guernica","Aluyis",R.drawable.cinco,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("La ultima cena","El orejas",R.drawable.seis,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("Joven de la perla","Miguel Angel",R.drawable.siete,R.drawable.baseline_favorite_24));
        listaFavoritos.add(new FavoritosVo("Nacimiento de Venus","Alguien",R.drawable.ocho,R.drawable.baseline_favorite_24));

    }
}
