package com.example.proyectofinalplataformas.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectofinalplataformas.Entitys.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void insert(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE id = :usuarioId")
    Usuario getUsuarioById(int usuarioId);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();
    @Query("SELECT * FROM usuario WHERE nombre = :nombreUsuario")
    Usuario getUser(String nombreUsuario);
}

