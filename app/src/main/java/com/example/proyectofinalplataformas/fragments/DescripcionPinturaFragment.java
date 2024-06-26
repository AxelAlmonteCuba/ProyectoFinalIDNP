package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
    }
}