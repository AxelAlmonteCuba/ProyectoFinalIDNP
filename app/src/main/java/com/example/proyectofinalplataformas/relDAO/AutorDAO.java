package com.example.proyectofinalplataformas.relDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.proyectofinalplataformas.Entitys.Autor;

@Dao
public interface AutorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAutor (Autor autor);
}
