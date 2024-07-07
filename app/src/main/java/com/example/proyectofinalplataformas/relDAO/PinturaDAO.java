package com.example.proyectofinalplataformas.relDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.proyectofinalplataformas.Entitys.Pintura;

@Dao
public interface PinturaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPintura (Pintura pintura);
}
