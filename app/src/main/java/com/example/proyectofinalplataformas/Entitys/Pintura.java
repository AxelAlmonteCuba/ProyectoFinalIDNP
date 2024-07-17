package com.example.proyectofinalplataformas.Entitys;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
public class Pintura {
    private String titulo;
    private String autor;
    private int año;
    private String descripcion;

    public Pintura(String titulo, String autor, int año, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAño() {
        return año;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
