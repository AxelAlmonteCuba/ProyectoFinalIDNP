package com.example.proyectofinalplataformas.Database;


import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MyMigrations {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Crear índice único para el título de la pintura
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_pintura_titulo ON pintura (titulo)");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Crear índice único para la combinación de usuarioId y pinturaId en la tabla Favoritos
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_favoritos_usuario_pintura ON favoritos (usuarioId, pinturaId)");
        }
    };
}

