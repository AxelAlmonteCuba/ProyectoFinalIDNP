package com.example.proyectofinalplataformas.Entitys;

import  androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    private int autorID;
    private String nombreA;
    private String apellido;

    public int getId() {
        return autorID;
    }

    public String getNombre() {
        return nombreA;
    }
    public void setNombre(String nombre) {
        this.nombreA = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
