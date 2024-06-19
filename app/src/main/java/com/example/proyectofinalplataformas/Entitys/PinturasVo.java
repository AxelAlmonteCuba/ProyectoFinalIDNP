package com.example.proyectofinalplataformas.Entitys;

public class PinturasVo {
    private String nombre;
    private String artista;
    private int foto;

    public PinturasVo(){

    }

    public PinturasVo(String nombre, String artista, int foto) {
        this.nombre = nombre;
        this.artista = artista;
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public int getFoto() {
        return foto;
    }
}
