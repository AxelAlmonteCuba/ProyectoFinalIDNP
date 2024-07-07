package com.example.proyectofinalplataformas.Database;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "art_gallery_database")
                .build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
