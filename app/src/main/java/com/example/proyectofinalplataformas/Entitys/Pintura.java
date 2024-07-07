package com.example.proyectofinalplataformas.Entitys;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    private int pinturaID;
    private String titulo;
    private int autorIDP;
    private int salaIDP;

    public int getSalaIDP() {
        return salaIDP;
    }

    public void setSalaIDP(int salaID) {
        this.salaIDP = salaID;
    }

    public int getAutorIDP() {
        return autorIDP;
    }

    public void setAutorIDP(int autorIDP) {
        this.autorIDP = autorIDP;
    }

    private String tecnica;
    private String categoria;
    private String descripcion;
    private int año;
    private int enlaceImg;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public int getPinturaID() {
        return pinturaID;
    }

    public void setPinturaID(int pinturaID) {
        this.pinturaID = pinturaID;
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

    public int getEnlaceImg() {
        return enlaceImg;
    }

    public void setEnlaceImg(int enlaceImg) {
        this.enlaceImg = enlaceImg;
    }
}
