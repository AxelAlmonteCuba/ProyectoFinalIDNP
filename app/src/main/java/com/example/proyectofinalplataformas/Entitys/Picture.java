package com.example.proyectofinalplataformas.Entitys;

public class Picture {
    private float[] coordinates;
    private String label;

    public Picture(float[] coordinates, String label) {
        this.coordinates = coordinates;
        this.label = label;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public String getLabel() {
        return label;
    }
}

