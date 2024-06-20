package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinalplataformas.Entitys.AccountEntity;
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.Repositorios.AccountRepository;
import com.google.gson.Gson;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String nombres;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombres = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView txtNameHome = view.findViewById(R.id.txtNamehome);
        String correo = getActivity().getIntent().getStringExtra("correo");
        String nombres = getActivity().getIntent().getStringExtra("nombres");
        txtNameHome.setText("Bienvenido "+nombres+" al");
        return view;
    }
}
