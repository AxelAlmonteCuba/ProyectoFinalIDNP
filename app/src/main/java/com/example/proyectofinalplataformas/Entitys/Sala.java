package com.example.proyectofinalplataformas.Entitys;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity

    public class Sala {
        @PrimaryKey(autoGenerate = true)
        private int salaID;
        private String nombreSala;
        private String tema;

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }



        public String getTema() {
            return tema;
        }
        public void setTema(String tema) {
            this.tema = tema;
        }

    public int getSalaID() {
        return salaID;
    }

    public void setSalaID(int salaID) {
        this.salaID = salaID;
    }
}
