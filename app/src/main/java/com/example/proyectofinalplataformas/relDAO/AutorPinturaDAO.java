package com.example.proyectofinalplataformas.relDAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.proyectofinalplataformas.Relaciones.AutorWithPintura;

import java.util.List;

@Dao
public interface AutorPinturaDAO {
    @Transaction
    @Query("SELECT * from Pintura, Autor where autorIDP = autorId ")
    public LiveData<List<AutorWithPintura>> PinturasPorAutor();


}
