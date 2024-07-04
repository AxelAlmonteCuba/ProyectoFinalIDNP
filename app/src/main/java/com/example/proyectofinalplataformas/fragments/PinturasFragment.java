package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinalplataformas.Adapters.AdaptadorPinturas;
import com.example.proyectofinalplataformas.Entitys.PinturasVo;
import com.example.proyectofinalplataformas.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PinturasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PinturasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NombreFragment = "";


    // TODO: Rename and change types of parameters
    private String nombreFragment;

    RecyclerView recyclerPinturas;
    ArrayList<PinturasVo> listaPinturas;


    public PinturasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment PinturasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PinturasFragment newInstance(String nombre) {
        PinturasFragment fragment = new PinturasFragment();
        Bundle args = new Bundle();
        args.putString(NombreFragment, nombre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombreFragment = getArguments().getString(NombreFragment);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_pinturas, container, false);
        TextView titulo = vista.findViewById(R.id.tituloGaleria);
        titulo.setText(nombreFragment);
        listaPinturas=new ArrayList<>();
        recyclerPinturas=vista.findViewById(R.id.recyclerId);
        recyclerPinturas.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();

        AdaptadorPinturas adapter= new AdaptadorPinturas(listaPinturas);
        recyclerPinturas.setAdapter(adapter);

        // Inflate the layout for this fragment
        return vista;
    }

    private void llenarLista() {
        listaPinturas.add(new PinturasVo("Mona Lisa","Davinci",R.drawable.uno));
        listaPinturas.add(new PinturasVo("Noche estrellada","Axelito",R.drawable.dos));
        listaPinturas.add(new PinturasVo("La ultima cena","Feorella",R.drawable.tres));
        listaPinturas.add(new PinturasVo("La gran ola","Raulin",R.drawable.cuatro));
        listaPinturas.add(new PinturasVo("Guernica","Aluyis",R.drawable.cinco));
        listaPinturas.add(new PinturasVo("La ultima cena","El orejas",R.drawable.seis));
        listaPinturas.add(new PinturasVo("Joven de la perla","Miguel Angel",R.drawable.siete));
        listaPinturas.add(new PinturasVo("Nacimiento de Venus","Alguien",R.drawable.ocho));

    }
}
