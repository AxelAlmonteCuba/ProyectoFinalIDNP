package com.example.proyectofinalplataformas.Relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;


import com.example.proyectofinalplataformas.Entitys.Autor;
import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.util.List;

public class AutorWithPintura {

    public String nombre_a;
    public String apellido_a2;
    public String titulo_p;
    public String tecninca_p;
    public String categoria_p;
    public String descripcion_p;
    public int año_p;
    public String enlace_p;
    @Embedded public Autor autor;
    @Relation(
            parentColumn = "autorID",
            entityColumn = "pinturaID"
    )
    public List<Pintura> pinturas;


}
