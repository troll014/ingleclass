package com.example.canabis.PicBrain;

import android.content.Context;
import androidx.room.Room;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;

public class DatabaseClient {
    private static final String TAG = "DatabaseClient";
    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        // Inicializa la base de datos
        Log.d(TAG, "Creando instancia de la base de datos...");
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "android_app_db")
                .fallbackToDestructiveMigration()
                .build();
        Log.d(TAG, "Instancia de la base de datos creada.");
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "Instancia de DatabaseClient es nula, creando nueva instancia...");
            instance = new DatabaseClient(context);
        } else {
            Log.d(TAG, "Usando instancia existente de DatabaseClient.");
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}

