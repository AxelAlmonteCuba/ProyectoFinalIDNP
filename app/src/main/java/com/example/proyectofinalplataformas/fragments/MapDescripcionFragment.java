package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinalplataformas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapDescripcionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapDescripcionFragment extends Fragment {

    private static final String ARG_ROOM_NAME = "room_name";
    private String roomName;

    public static MapDescripcionFragment newInstance(String roomName) {
        MapDescripcionFragment fragment = new MapDescripcionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROOM_NAME, roomName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomName = getArguments().getString(ARG_ROOM_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_descripcion, container, false);
        TextView textView = view.findViewById(R.id.room_name);
        textView.setText(roomName);
        return view;
    }
}