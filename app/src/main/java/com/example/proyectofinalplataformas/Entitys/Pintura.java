package com.example.proyectofinalplataformas.Entitys;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "pintura", indices = {@Index(value = "titulo", unique = true)})
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String autor;
    public int año;
    public String descripcion;
    public String tecnica;
    public String galeria;
    public int img;

    public Pintura(String titulo, String autor, int año, String descripcion, String tecnica, String galeria, int img) {

        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.descripcion = descripcion;
        this.tecnica = tecnica;
        this.galeria = galeria;
        this.img = img;
    }

    public int getId() {
        return id;
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

    public String getTecnica() {
        return tecnica;
    }

    public String getGaleria() {
        return galeria;
    }

    public int getImg() {
        return img;
    }
}
