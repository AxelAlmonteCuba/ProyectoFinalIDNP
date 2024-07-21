package com.example.proyectofinalplataformas.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;

import com.example.proyectofinalplataformas.R;

import java.util.Locale;

public class AudioService extends Service {
    private TextToSpeech tts;
    private static final int NOTIF_ID = 1;
    private static final String CHANNEL_ID = "AudioServiceChannel";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String texto = intent.getStringExtra("texto");

        if (texto != null) {
            iniciarTextToSpeech(texto);
            crearNotificacion();
        }

        return START_NOT_STICKY;
    }

    private void iniciarTextToSpeech(String texto) {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("es", "ES"));
                tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    private void crearNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Audio Service Channel",
                    NotificationManager.IMPORTANCE_HIGH // Cambia a HIGH para mayor visibilidad
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null && manager.getNotificationChannel(CHANNEL_ID) == null) {
                manager.createNotificationChannel(channel);
            }
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproducción de audio")
                .setContentText("Toca para detener")
                .setSmallIcon(R.drawable.baseline_play_circle_outline_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Asegura prioridad alta
                .setOngoing(true)
                .build();

        startForeground(NOTIF_ID, notification);
    }


    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
