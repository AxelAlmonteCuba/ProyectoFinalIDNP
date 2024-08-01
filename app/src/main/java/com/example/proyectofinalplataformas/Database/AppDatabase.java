package com.example.proyectofinalplataformas.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proyectofinalplataformas.DAO.FavoritosDao;
import com.example.proyectofinalplataformas.DAO.PinturaDao;
import com.example.proyectofinalplataformas.DAO.UsuarioDao;
import com.example.proyectofinalplataformas.Entitys.Autor;
import com.example.proyectofinalplataformas.Entitys.Favoritos;
import com.example.proyectofinalplataformas.Entitys.Galeria;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Usuario;

@Database(entities = {Pintura.class, Galeria.class, Autor.class, Usuario.class, Favoritos.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PinturaDao pinturaDao();
    public abstract FavoritosDao favoritosDao();
    public abstract UsuarioDao usuarioDao();
}
