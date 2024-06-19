package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerPinturas;
    ArrayList<PinturasVo> listaPinturas;

    public PinturasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PinturasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PinturasFragment newInstance(String param1, String param2) {
        PinturasFragment fragment = new PinturasFragment();
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
        View vista=inflater.inflate(R.layout.fragment_pinturas, container, false);
        listaPinturas=new ArrayList<>();
        recyclerPinturas=vista.findViewById(R.id.recyclerId);
        recyclerPinturas.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();

        // Inflate the layout for this fragment
        return vista;
    }

    private void llenarLista() {
        listaPinturas.add(new PinturasVo("Mono liso 1","Davinci",R.drawable.uno));
        listaPinturas.add(new PinturasVo("Mono liso 2","Axelito",R.drawable.dos));
        listaPinturas.add(new PinturasVo("Mono liso 3","Feorella",R.drawable.tres));
        listaPinturas.add(new PinturasVo("Mono liso 4","Raulin",R.drawable.cuatro));
        listaPinturas.add(new PinturasVo("Mono liso 5","Aluyis",R.drawable.cinco));
        listaPinturas.add(new PinturasVo("Mono liso 6","El orejas",R.drawable.seis));
        listaPinturas.add(new PinturasVo("Mono liso 7","Miguel Angel",R.drawable.siete));
        listaPinturas.add(new PinturasVo("Mono liso 8","Alguien",R.drawable.ocho));

    }
}