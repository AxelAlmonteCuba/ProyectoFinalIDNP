package com.example.proyectofinalplataformas.Entitys;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
