package com.example.proyectofinalplataformas.Relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;


import com.example.proyectofinalplataformas.Entitys.Autor;
import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.util.List;

public class AutorWithPintura {


    @Embedded public Autor autor;
    @Relation(
            parentColumn = "autorID",
            entityColumn = "autorIDP"
    )
    public List<Pintura> pinturas;


}
