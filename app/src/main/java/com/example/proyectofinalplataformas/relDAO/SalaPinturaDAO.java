package com.example.proyectofinalplataformas.relDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
import com.example.proyectofinalplataformas.Relaciones.SalaWithPintura;
@Dao
public interface SalaPinturaDAO {
    @Transaction
    @Query("SELECT nombreSala, titulo, nombreA, enlaceImg FROM Sala, Autor, Pintura WHERE salaIDP =  salaID")
    public LiveData<List<SalaWithPintura>> loadUserAndBookNames();

}
