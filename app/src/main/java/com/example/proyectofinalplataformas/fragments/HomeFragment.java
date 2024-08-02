package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.proyectofinalplataformas.R;

public class HomeFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombres";

    private String nombres;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String nombres) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombres);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombres = getArguments().getString(ARG_NOMBRE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView txtNameHome = view.findViewById(R.id.txtNamehome);
        if (nombres != null && !nombres.isEmpty()) {
            txtNameHome.setText("Bienvenido " + nombres + " al Centro Cultural UNSA");
        } else {
            txtNameHome.setText("Bienvenido al Centro Cultural UNSA");
        }
        return view;
    }
}
