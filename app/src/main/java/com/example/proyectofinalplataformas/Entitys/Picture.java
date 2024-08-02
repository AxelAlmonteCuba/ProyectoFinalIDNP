package com.example.proyectofinalplataformas.Entitys;

public class Picture {
    private float[] coordinates;
    private String label;
    private String titulo;

    public Picture(float[] coordinates, String label,String titulo) {
        this.coordinates = coordinates;
        this.label = label;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public String getLabel() {
        return label;
    }
}

