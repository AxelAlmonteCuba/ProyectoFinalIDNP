package com.example.proyectofinalplataformas.Relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Sala;

import java.util.List;

public class SalaWithPintura {

    @Embedded
    public Sala sala;

    @Relation(
            parentColumn = "salaID",
            entityColumn = "salaIDP"
    )
    public List<Pintura> pinturas;
}
