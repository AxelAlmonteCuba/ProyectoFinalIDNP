package com.example.proyectofinalplataformas.relDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.proyectofinalplataformas.Entitys.Sala;
import com.example.proyectofinalplataformas.Relaciones.SalaWithPintura;

import java.util.List;

@Dao
public interface SalaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSala(Sala sala);

    @Transaction
    @Query("SELECT * FROM sala")
    List<SalaWithPintura> getSalasConPinturas();
}
