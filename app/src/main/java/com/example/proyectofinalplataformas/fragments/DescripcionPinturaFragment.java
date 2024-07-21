package com.example.proyectofinalplataformas.fragments;

import  android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.reflect.Field;

import com.example.proyectofinalplataformas.CSV.LectorCSV;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescripcionPinturaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescripcionPinturaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "nombrePintura";


    // TODO: Rename and change types of parameters
    private String nombre;


    public DescripcionPinturaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DescripcionPinturaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescripcionPinturaFragment newInstance(String nombrePintura) {
        DescripcionPinturaFragment fragment = new DescripcionPinturaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nombrePintura);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descripcion_pintura, container, false);
        LectorCSV lectorCSV = new LectorCSV(getActivity());
        Pintura pintura = lectorCSV.obtenerPinturaPorTitulo(nombre);


        if (pintura != null) {
            Log.d("descriotion",nombre);
            TextView tituloTextView = view.findViewById(R.id.txtTituloPinturaDescripcion);
            TextView autorTextView = view.findViewById(R.id.txtArtistaDesc);
            TextView añoTextView = view.findViewById(R.id.txtFechaDesc);
            TextView descripcionTextView = view.findViewById(R.id.txtDescripcionDesc);

            tituloTextView.setText(pintura.getTitulo());
            autorTextView.setText(pintura.getAutor());
            añoTextView.setText(String.valueOf(pintura.getAño()));
            descripcionTextView.setText(pintura.getDescripcion());
            logAllDrawableIds();
        }
        return view;
    }


    public void logAllDrawableIds() {
        Field[] fields = R.drawable.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                int drawableId = field.getInt(null);
                String name = field.getName();
                Log.d("DrawableID", "Name: " + name + " ID: " + drawableId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}