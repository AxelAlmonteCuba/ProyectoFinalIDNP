package com.example.proyectofinalplataformas.Database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proyectofinalplataformas.Entitys.Autor;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.Entitys.Sala;
import com.example.proyectofinalplataformas.R;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseCallback extends RoomDatabase.Callback {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        executorService.execute(() -> {
        // Obtener la instancia de la base de datos
            AppDatabase database = MyApplication.getDatabase();

            Sala sala1 = new Sala();
            sala1.setNombreSala("Galeria 1");
            sala1.setTema("Galeria para Maestros");


            Autor autor1 = new Autor();
            autor1.setNombreA("Leonardo");
            autor1.setApellido("Davincci");
            database.autorDAO().insertAutor(autor1);

            Pintura pintura1 = new Pintura();
            pintura1.setAño(1503);
            pintura1.setCategoria("Retrato");
            pintura1.setDescripcion("El retrato de Lisa Gherardini, esposa de Francesco del Giocondo, \n más conocido como La Gioconda o Monna Lisa, es una obra pictórica \n del polímata renacentista italiano Leonardo da Vinci.\nFue adquirida por el rey Francisco I de Francia a comienzos del siglo XVI \n y desde entonces es propiedad del Estado francés");
            pintura1.setEnlaceImg(R.drawable.uno);
            pintura1.setSalaIDP(sala1.getSalaID());
            pintura1.setAutorIDP(autor1.getAutorID());
            pintura1.setTecnica("sfumato");
            pintura1.setTitulo("Monalisa");
            database.pinturaDao().insertPintura(pintura1);

            database.salaDAO().insertSala(sala1);
        });
    }
}
