package com.example.proyectofinalplataformas.Entitys;

public class FavoritosVo {
    private String nombre;
    private String artista;
    private int foto;
    private int corazon;

    public FavoritosVo(){

    }

    public FavoritosVo(String nombre, String artista, int foto, int corazon) {
        this.nombre = nombre;
        this.artista = artista;
        this.foto = foto;
        this.corazon = corazon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getCorazon() {
        return corazon;
    }

    public void setCorazon(int corazon) {
        this.corazon = corazon;
    }
}
