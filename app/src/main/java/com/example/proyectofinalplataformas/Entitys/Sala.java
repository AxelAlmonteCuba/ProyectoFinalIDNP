package com.example.proyectofinalplataformas.Entitys;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity

public class Sala {
    @PrimaryKey(autoGenerate = true)
    private int salaID;
    private String nombreSala;
    private String tema;
    private List<Pintura> pinturas;
    public int getId() {
        return salaID;
    }
    public String getNombre() {
        return nombreSala;
    }
    public void setNombre(String nombre) {
        this.nombreSala = nombre;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public List<Pintura> getPinturas() {
        return pinturas;
    }
    public void setPinturas(List<Pintura> pinturas) {
        this.pinturas = pinturas;
    }

}
