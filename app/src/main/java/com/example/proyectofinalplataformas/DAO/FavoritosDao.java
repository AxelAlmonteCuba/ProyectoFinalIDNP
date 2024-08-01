package com.example.proyectofinalplataformas.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

import com.example.proyectofinalplataformas.Entitys.Favoritos;
import com.example.proyectofinalplataformas.Entitys.Pintura;

import java.util.List;

@Dao
public interface FavoritosDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Favoritos favoritos) throws Exception;

    @Query("SELECT * FROM pintura WHERE id IN (SELECT pinturaId FROM favoritos WHERE usuarioId = :usuarioId)")
    List<Pintura> getFavoritosPorUsuario(int usuarioId);
    @Query("DELETE FROM favoritos WHERE usuarioId = :usuarioId AND pinturaId = :pinturaId")
    void deleteFavorito(int usuarioId, int pinturaId);
}
