package com.example.proyectofinalplataformas.CSV;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.io.BufferedReader;
import java.io.FileOutputStream;
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
        int contador = 0;
        try {
            InputStream is = contexto.getAssets().open("paitings.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(";");

                if (tokens.length == 7 && contador > 0) {
                    String titulo = tokens[0];
                    String autor = tokens[1];
                    Log.d("Lector", titulo);
                    int año = Integer.parseInt(tokens[2]);
                    String descripcion = tokens[3];
                    String tecnica = tokens[4];
                    String galeria = tokens[5];
                    int img = Integer.parseInt(tokens[6]);
                    pinturas.add(new Pintura(titulo, autor, año, descripcion, tecnica, galeria, img));
                }
                contador++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pinturas;
    }



    public boolean verificarSiEsFavorito(Pintura pintura) {
        try {
            InputStream is = contexto.openFileInput("favoritos.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");
                if (tokens.length > 0 && tokens[0].equalsIgnoreCase(pintura.getTitulo())) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void agregarAFavoritos(Pintura pintura) {
        if (!verificarSiEsFavorito(pintura)) {
            try {
                FileOutputStream fos = contexto.openFileOutput("favoritos.csv", Context.MODE_APPEND);
                String linea = pintura.getTitulo() + "," + pintura.getAutor() + "," + pintura.getAño() + "," +
                        pintura.getDescripcion() + "," + pintura.getTecnica() + "," + pintura.getGaleria() + "," +
                        pintura.getImg() + "\n";
                fos.write(linea.getBytes());
                fos.close();
                Toast.makeText(contexto, "Agregado a favoritos", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(contexto, "Error al agregar a favoritos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(contexto, "Ya está en favoritos", Toast.LENGTH_SHORT).show();
        }
    }

    public void removerDeFavoritos(Pintura pintura) {
        try {
            InputStream is = contexto.openFileInput("favoritos.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");
                if (!tokens[0].equalsIgnoreCase(pintura.getTitulo())) {
                    sb.append(linea).append("\n");
                }
            }
            reader.close();

            FileOutputStream fos = contexto.openFileOutput("favoritos.csv", Context.MODE_PRIVATE);
            fos.write(sb.toString().getBytes());
            fos.close();

            Toast.makeText(contexto, "Removido de favoritos", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(contexto, "Error al remover de favoritos", Toast.LENGTH_SHORT).show();
        }
    }
}
