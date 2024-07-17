package com.example.proyectofinalplataformas.CSV;

import android.content.Context;

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
        try {
            InputStream is = contexto.getAssets().open("paintings.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");
                if (tokens.length == 4) {
                    String titulo = tokens[0];
                    String autor = tokens[1];
                    int año = Integer.parseInt(tokens[2]);
                    String descripcion = tokens[3];
                    pinturas.add(new Pintura(titulo, autor, año, descripcion));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pinturas;
    }

    public Pintura obtenerPinturaPorTitulo(String titulo) {
        for (Pintura pintura : cargarPinturas()) {
            if (pintura.getTitulo().equalsIgnoreCase(titulo)) {
                return pintura;
            }
        }
        return null;
    }
}

