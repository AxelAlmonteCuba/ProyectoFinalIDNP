package com.example.proyectofinalplataformas.Relaciones;


import androidx.room.Embedded;
import androidx.room.Relation;


import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Galeria;

import java.util.List;

public class SalaWithPintura {
    @Embedded
    public Galeria sala;
    @Relation(
            parentColumn = "salaID",
            entityColumn = "pinturaID"
    )
    public List<Pintura> pinturas;
}