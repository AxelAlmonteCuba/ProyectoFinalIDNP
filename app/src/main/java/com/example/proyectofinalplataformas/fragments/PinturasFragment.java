package com.example.proyectofinalplataformas.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalplataformas.Adapters.AdaptadorPinturas;
import com.example.proyectofinalplataformas.Database.AppDatabase;
import com.example.proyectofinalplataformas.Database.DatabaseClient;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.PinturasVo;
import com.example.proyectofinalplataformas.R;

import java.util.ArrayList;
import java.util.List;

public class PinturasFragment extends Fragment {

    private static final String NombreFragment = "nombreFragment";

    private String nombreFragment;
    private DescripcionPinturaFragment descripcionPinturaFragment = null;

    RecyclerView recyclerPinturas;
    ArrayList<PinturasVo> listaPinturas;

    public PinturasFragment() {
        // Required empty public constructor
    }

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
        View vista = inflater.inflate(R.layout.fragment_pinturas, container, false);
        TextView tituloGaleria = vista.findViewById(R.id.tituloGaleria);
        tituloGaleria.setText("Galeria " + nombreFragment);
        listaPinturas = new ArrayList<>();
        recyclerPinturas = vista.findViewById(R.id.recyclerId);
        recyclerPinturas.setLayoutManager(new LinearLayoutManager(getContext()));

        new FetchPinturasTask().execute();

        AdaptadorPinturas adapter = new AdaptadorPinturas(listaPinturas);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descripcionPinturaFragment = DescripcionPinturaFragment.newInstance(listaPinturas.get(recyclerPinturas.getChildAdapterPosition(v)).getNombre());
                Log.d("name",listaPinturas.get(recyclerPinturas.getChildAdapterPosition(v)).getNombre());
                LoadFragment(descripcionPinturaFragment);
            }
        });
        recyclerPinturas.setAdapter(adapter);

        return vista;
    }

    private class FetchPinturasTask extends AsyncTask<Void, Void, List<Pintura>> {
        @Override
        protected List<Pintura> doInBackground(Void... voids) {
            AppDatabase db = DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase();
            return db.pinturaDao().getPinturasPorGaleria(nombreFragment);
        }

        @Override
        protected void onPostExecute(List<Pintura> listaPinturasLis) {
            for (Pintura paint : listaPinturasLis) {
                PinturasVo pinturasVo = new PinturasVo(paint.getTitulo(), paint.getAutor(), paint.getImg());
                listaPinturas.add(pinturasVo);
            }
            recyclerPinturas.getAdapter().notifyDataSetChanged();
        }
    }

    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Home_Conteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
