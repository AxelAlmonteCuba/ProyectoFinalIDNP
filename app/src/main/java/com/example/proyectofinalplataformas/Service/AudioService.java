package com.example.proyectofinalplataformas.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
    public static final String TEXT = "TEXT";
    public static final String COMMAND = "COMMAND";
    public static final String START = "START";
    public static final String STOP = "STOP";
    public static final String CHANNEL_ID = "AudioPlayServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("es", "ES"));
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra(TEXT);
        String command = intent.getStringExtra(COMMAND);

        if (START.equals(command)) {
            startForegroundService(text);
        } else if (STOP.equals(command)) {
            stopForeground(true);
            tts.stop();
        }

        return START_STICKY;
    }

    private void startForegroundService(String text) {
        createNotificationChannel();

        Intent stopIntent = new Intent(this, AudioService.class);
        stopIntent.putExtra(COMMAND, STOP);
        PendingIntent pendingStopIntent = PendingIntent.getService(
                this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproducción de Audio")
                .setContentText("Leyendo la información de la pintura")
                .setSmallIcon(R.drawable.ic_notification)
                .addAction(R.drawable.ic_stop, "Detener", pendingStopIntent)
                .build();

        startForeground(1, notification);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "Audio Play Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onDestroy() {
        tts.stop();
        tts.shutdown();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
