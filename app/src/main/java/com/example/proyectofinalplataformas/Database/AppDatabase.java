package com.example.proyectofinalplataformas.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proyectofinalplataformas.Entitys.Autor;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Sala;
import com.example.proyectofinalplataformas.relDAO.AutorDAO;
import com.example.proyectofinalplataformas.relDAO.AutorPinturaDAO;
import com.example.proyectofinalplataformas.relDAO.PinturaDAO;
import com.example.proyectofinalplataformas.relDAO.SalaDAO;
import com.example.proyectofinalplataformas.relDAO.SalaPinturaDAO;

@Database(entities = {Sala.class, Autor.class, Pintura.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract AutorPinturaDAO autorPinturaDAO();
    public abstract PinturaDAO pinturaDao();
    public abstract AutorDAO autorDAO();
    public abstract SalaDAO salaDAO();
    public abstract SalaPinturaDAO salaPinturaDAO();
}
