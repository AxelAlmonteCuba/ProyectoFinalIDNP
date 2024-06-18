package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.proyectofinalplataformas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PinturasFragment pinturasFragment = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriasFragment newInstance(String param1, String param2) {
        GaleriasFragment fragment = new GaleriasFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galerias, container, false);
        ImageView imgGaleria1 = view.findViewById(R.id.imgGaleria1);
        ImageView imgGaleria2 = view.findViewById(R.id.imgGaleria2);
        ImageView imgGaleria3 = view.findViewById(R.id.imgGaleria3);
        ImageView imgGaleria4 = view.findViewById(R.id.imgGaleria4);
        ImageView imgGaleria5 = view.findViewById(R.id.imgGaleria5);
        ImageView imgGaleria6 = view.findViewById(R.id.imgGaleria6);
        ImageView imgGaleria7 = view.findViewById(R.id.imgGaleria7);

        imgGaleria1.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria2.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria3.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria4.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria5.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria6.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        imgGaleria7.setOnClickListener(v -> {
            pinturasFragment = PinturasFragment.newInstance("","");
            LoadFragment(pinturasFragment);
        });
        return view;
    }
    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Home_Conteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}