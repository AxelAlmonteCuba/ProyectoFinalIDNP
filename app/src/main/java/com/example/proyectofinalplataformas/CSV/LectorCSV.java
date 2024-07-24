package com.example.proyectofinalplataformas.CSV;

import android.content.Context;
import android.util.Log;

import androidx.activity.result.contract.ActivityResultContracts;

import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    private Context contexto;

    public LectorCSV(Context contexto) {
        this.contexto = contexto;
    }

    public List<Pintura> cargarPinturas() {
        List<Pintura> pinturas = new ArrayList<>();
        int conttador = 0;
        try {
            InputStream is = contexto.getAssets().open("paitings.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");

                if (tokens.length == 7 && conttador > 0) {
                    String titulo = tokens[0];
                    String autor = tokens[1];
                    Log.d("klecotr",titulo);
                    int año = Integer.parseInt(tokens[2]);
                    String descripcion = tokens[3];
                    String tecnica = tokens[4];
                    String galeria = tokens[5];
                    int img = Integer.parseInt(tokens[6]);
                    pinturas.add(new Pintura(titulo, autor, año, descripcion,tecnica,galeria,img));
                }
                conttador++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pinturas;
    }

    public Pintura obtenerPinturaPorTitulo(String titulo) {
        for (Pintura pintura : cargarPinturas()) {
            if (pintura.getTitulo().trim().equalsIgnoreCase(titulo.trim())) {
                return pintura;
            }
        }
        return null;
    }

    public ArrayList<Pintura> obtenerPinturaPorGaleria(String galeria) {
        ArrayList<Pintura> pinturas = new ArrayList<Pintura>();
        for (Pintura pintura : cargarPinturas()) {
            if (pintura.getGaleria().trim().equalsIgnoreCase(galeria.trim())) {
                pinturas.add(pintura);
            }
        }
        return pinturas;
    }

}

