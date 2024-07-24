package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalplataformas.Adapters.AdaptadorPinturas;
import com.example.proyectofinalplataformas.CSV.LectorCSV;
import com.example.proyectofinalplataformas.Entitys.Pintura;
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
    private static final String NombreFragment = "nombreFragment";


    // TODO: Rename and change types of parameters
    private String nombreFragment;
    private DescripcionPinturaFragment descripcionPinturaFragment = null;

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
        TextView tituloGakeria = vista.findViewById(R.id.tituloGaleria);
        tituloGakeria.setText("Galeria "+nombreFragment);
        listaPinturas=new ArrayList<>();
        recyclerPinturas=vista.findViewById(R.id.recyclerId);
        recyclerPinturas.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();

        AdaptadorPinturas adapter= new AdaptadorPinturas(listaPinturas);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccion: " + listaPinturas.get(recyclerPinturas.getChildAdapterPosition(v)).getNombre(),
                        Toast.LENGTH_SHORT).show();
                descripcionPinturaFragment = DescripcionPinturaFragment.newInstance(listaPinturas.get(recyclerPinturas.getChildAdapterPosition(v)).getNombre());
                LoadFragment(descripcionPinturaFragment);
            }
        });
        recyclerPinturas.setAdapter(adapter);

        // Inflate the layout for this fragment
        return vista;
    }

    private void llenarLista() {
        LectorCSV lectorCSV = new LectorCSV(getActivity());
        ArrayList<Pintura> pinturas = lectorCSV.obtenerPinturaPorGaleria(nombreFragment);

        for (Pintura pintura:pinturas) {
            listaPinturas.add(new PinturasVo(pintura.getTitulo(),pintura.getAutor(),pintura.getImg()));
        }

        listaPinturas.add(new PinturasVo("El grito","Davinci",R.drawable.uno));
        listaPinturas.add(new PinturasVo("PICNIC: REMINISCENCIAS SOBRE LA MESA","Axelito",R.drawable.dos));
        listaPinturas.add(new PinturasVo("Mono liso 3","Feorella",R.drawable.tres));
        listaPinturas.add(new PinturasVo("Mono liso 4","Raulin",R.drawable.cuatro));
        listaPinturas.add(new PinturasVo("Mono liso 5","Aluyis",R.drawable.cinco));
        listaPinturas.add(new PinturasVo("Mono liso 6","El orejas",R.drawable.seis));
        listaPinturas.add(new PinturasVo("Mono liso 7","Miguel Angel",R.drawable.siete));
        listaPinturas.add(new PinturasVo("Mono liso 8","Alguien",R.drawable.ocho));

    }
    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Home_Conteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
