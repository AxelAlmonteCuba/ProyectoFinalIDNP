package com.example.proyectofinalplataformas.Entitys;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "autor")
public class Autor {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String apellido;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
