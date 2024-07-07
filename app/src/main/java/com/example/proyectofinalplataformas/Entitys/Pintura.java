package com.example.proyectofinalplataformas.Entitys;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    private int pinturaID;
    private String titulo;
    private Autor autorIDP;
    private String salaIDP;
    private String tecnica;
    private String categoria;
    private String descripcion;
    private int año;
    private String enlaceImg;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autorIDP;
    }

    public void setAutor(Autor autor) {
        this.autorIDP = autor;
    }

    public String getSala() {
        return salaIDP;
    }

    public void setSala(String sala) {
        this.salaIDP = sala;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getId() {
        return pinturaID;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getEnlaceImg() {
        return enlaceImg;
    }

    public void setEnlaceImg(String enlaceImg) {
        this.enlaceImg = enlaceImg;
    }
}
