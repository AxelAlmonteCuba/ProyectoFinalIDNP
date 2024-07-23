package com.example.proyectofinalplataformas.Entitys;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
public class Pintura {
    private String titulo;
    private String autor;
    private int año;
    private String descripcion;
    private String tecnica;
    private String galeria;
    private int img;

    public Pintura(String titulo, String autor, int año, String descripcion, String tecnica, String galeria, int img) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.descripcion = descripcion;
        this.tecnica = tecnica;
        this.galeria = galeria;
        this.img = img;
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

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
