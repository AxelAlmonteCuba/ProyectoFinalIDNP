package com.example.proyectofinalplataformas.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.util.List;

@Dao
public interface PinturaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Pintura pintura) throws Exception;


    @Query("SELECT * FROM pintura WHERE galeria = :galeriaNombre")
    List<Pintura> getPinturasPorGaleria(String galeriaNombre);
    @Query("SELECT * FROM pintura WHERE titulo = :tituloP")
    Pintura getPintura(String tituloP);
    @Query("SELECT * FROM pintura")
    List<Pintura> getPinturas();
    @Query("SELECT COUNT(*) FROM pintura WHERE titulo = :titulo")
    int countByTitulo(String titulo);
}