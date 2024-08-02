package com.example.proyectofinalplataformas.fragments;

import  android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.CSV.LectorCSV;
import com.example.proyectofinalplataformas.Entitys.Picture;
import com.example.proyectofinalplataformas.Entitys.Room;
import java.util.List;
import com.example.proyectofinalplataformas.view.MapView;

public class MapFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public MapFragment() {
    }
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Leer datos de los archivos CSV
        List<Room> rooms = LectorCSV.readRoomsFromCSV(getContext(), "rooms.csv");
        List<Picture> pictures = LectorCSV.readPicturesFromCSV(getContext(), "pictures.csv");

        // Crear una instancia de CanvasView y agregarlo al contenedor
        MapView canvasView = new MapView(getContext(), rooms, pictures);
        ViewGroup canvasContainer = view.findViewById(R.id.canvas_container);
        canvasContainer.addView(canvasView);

        return view;
    }

}