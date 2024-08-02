package com.example.proyectofinalplataformas.CSV;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Picture;
import com.example.proyectofinalplataformas.Entitys.Room;

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

    public List<Pintura> cargarPinturas(String archivo) {
        List<Pintura> pinturas = new ArrayList<>();
        int contador = 0;
        try {
            InputStream is = contexto.getAssets().open(archivo + ".csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");

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

    public Pintura obtenerPinturaPorTitulo(String titulo, String archivo) {
        for (Pintura pintura : cargarPinturas(archivo)) {
            if (pintura.getTitulo().trim().equalsIgnoreCase(titulo.trim())) {
                return pintura;
            }
        }
        return null;
    }

    public ArrayList<Pintura> obtenerPinturaPorGaleria(String galeria) {
        ArrayList<Pintura> pinturas = new ArrayList<>();
        for (Pintura pintura : cargarPinturas("paintings")) {
            if (pintura.getGaleria().trim().equalsIgnoreCase(galeria.trim())) {
                pinturas.add(pintura);
            }
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
    public static List<Room> readRoomsFromCSV(Context context, String fileName) {
        List<Room> rooms = new ArrayList<>();
        List<String[]> roomData = readCSV(context, fileName);
        for (String[] room : roomData) {
            if (room.length < 3) {
                continue;
            }
            String name = room[0];
            float[][] coordinates = new float[(room.length - 1) / 2][2];
            for (int i = 1, j = 0; i < room.length; i += 2, j++) {
                coordinates[j][0] = Float.parseFloat(room[i]);
                coordinates[j][1] = Float.parseFloat(room[i + 1]);
            }
            rooms.add(new Room(name, coordinates));
        }
        return rooms;
    }

    public static List<Picture> readPicturesFromCSV(Context context, String fileName) {
        List<Picture> pictures = new ArrayList<>();
        List<String[]> pictureData = readCSV(context, fileName);
        for (String[] picture : pictureData) {
            if (picture.length < 3) {
                continue;
            }
            String label = picture[0];
            float[] coordinates = new float[2];
            coordinates[0] = Float.parseFloat(picture[1]);
            coordinates[1] = Float.parseFloat(picture[2]);
            pictures.add(new Picture(coordinates, label));
        }
        return pictures;
    }

    private static List<String[]> readCSV(Context context, String fileName) {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
