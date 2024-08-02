package com.example.proyectofinalplataformas.Entitys;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoritos", indices = {@Index(value = {"usuarioId", "pinturaId"}, unique = true)})
public class Favoritos {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int usuarioId;
    public int pinturaId;

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getPinturaId() {
        return pinturaId;
    }
}
