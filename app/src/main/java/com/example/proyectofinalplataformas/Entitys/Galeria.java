package com.example.proyectofinalplataformas.Entitys;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "galeria")
public class Galeria {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String tema;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTema() {
        return tema;
    }
}
