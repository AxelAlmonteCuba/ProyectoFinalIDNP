package com.example.proyectofinalplataformas.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proyectofinalplataformas.Entitys.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE id = :usuarioId")
    Usuario getUsuarioById(int usuarioId);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsuarios();

    @Query("SELECT * FROM usuario WHERE nombre = :nombreUsuario AND apellido = :apellidoUsuario")
    Usuario getUserByNombreApellido(String nombreUsuario, String apellidoUsuario);

    default void insertUnique(Usuario usuario) {
        Usuario existingUser = getUserByNombreApellido(usuario.getNombre(), usuario.getApellido());
        if (existingUser == null) {
            insert(usuario);
        }
    }
}
