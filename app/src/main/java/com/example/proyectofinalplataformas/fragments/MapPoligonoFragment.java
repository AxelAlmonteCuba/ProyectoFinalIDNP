package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.FrameLayout;

import com.example.proyectofinalplataformas.Entitys.Picture;
import com.example.proyectofinalplataformas.Entitys.Room;
import com.example.proyectofinalplataformas.view.MapView;
import java.util.List;
import java.util.ArrayList;
import com.example.proyectofinalplataformas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapPoligonoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPoligonoFragment extends Fragment {

    private static final String ARG_ROOMS = "rooms";
    private static final String ARG_PICTURES = "pictures";

    private List<Room> rooms;
    private List<Picture> pictures;

    public MapPoligonoFragment() {}

    public static MapPoligonoFragment newInstance(List<Room> rooms, List<Picture> pictures) {
        MapPoligonoFragment fragment = new MapPoligonoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ROOMS, (ArrayList<Room>) rooms);
        args.putSerializable(ARG_PICTURES, (ArrayList<Picture>) pictures);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rooms = (List<Room>) getArguments().getSerializable(ARG_ROOMS);
            pictures = (List<Picture>) getArguments().getSerializable(ARG_PICTURES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_poligono, container, false);

        // Crear una instancia de CanvasView y agregarlo al contenedor
        MapView canvasView = new MapView(getContext(), rooms, pictures);
        FrameLayout frameLayout = view.findViewById(R.id.canvas_container);
        frameLayout.addView(canvasView);

        return view;
    }
}