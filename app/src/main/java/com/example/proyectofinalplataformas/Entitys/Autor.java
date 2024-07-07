package com.example.proyectofinalplataformas.Entitys;

import  androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    private int autorID;



    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }



    private String nombreA;
    private String apellido;


    public String getNombreA() {
        return nombreA;
    }
    public void setNombreA(String nombre) {
        this.nombreA = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getAutorID() {
        return autorID;
    }
}
