package com.example.proyectofinalplataformas.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import com.example.proyectofinalplataformas.HomeActivity;
import com.example.proyectofinalplataformas.R;
import java.util.Locale;

public class AudioService extends Service {
    private TextToSpeech textToSpeech;
    private Handler serviceHandler;
    private HandlerThread handlerThread;
    private String currentText = null;
    private boolean isSpeaking = false;

    public static final String TEXT = "TEXT";
    public static final String COMMAND = "COMMAND";
    public static final String PLAY = "PLAY";
    public static final String PAUSE = "PAUSE";
    public static final String RESUME = "RESUME";
    public static final String STOP = "STOP";
    public static final String CHANNEL_ID = "AudioPlayServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("AudioPlayServiceThread");
        handlerThread.start();
        serviceHandler = new Handler(handlerThread.getLooper());

        textToSpeech = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                Locale locale = new Locale("es", "ES");
                int result = textToSpeech.setLanguage(locale);

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Manejo de error si el idioma no está disponible
                    Log.e("AudioService", "Idioma no soportado o datos faltantes");
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        handlerThread.quitSafely();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra(TEXT);
        String command = intent.getStringExtra(COMMAND);

        if (command != null) {
            switch (command) {
                case PLAY:
                    serviceHandler.post(() -> {
                        startSpeaking(text);
                        startForegroundService(true);
                    });
                    break;
                case PAUSE:
                    serviceHandler.post(() -> {
                        pauseSpeaking();
                        startForegroundService(false);
                    });
                    break;
                case RESUME:
                    serviceHandler.post(() -> {
                        resumeSpeaking();
                        startForegroundService(true);
                    });
                    break;
                case STOP:
                    serviceHandler.post(() -> {
                        stopSpeaking();
                        stopForeground(true);
                    });
                    break;
            }
        }

        return START_STICKY;
    }

    private void startSpeaking(String text) {
        if (text != null && !text.isEmpty()) {
            currentText = text;
            isSpeaking = true;
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void pauseSpeaking() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            isSpeaking = false;
        }
    }

    private void resumeSpeaking() {
        if (currentText != null && !currentText.isEmpty()) {
            isSpeaking = true;
            textToSpeech.speak(currentText, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void stopSpeaking() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            currentText = null;
            isSpeaking = false;
        }
    }

    private void startForegroundService(boolean isSpeaking) {
        createNotificationChannel();

        Intent playIntent = new Intent(this, AudioService.class);
        playIntent.putExtra(COMMAND, PLAY);
        Intent pauseIntent = new Intent(this, AudioService.class);
        pauseIntent.putExtra(COMMAND, PAUSE);
        Intent resumeIntent = new Intent(this, AudioService.class);
        resumeIntent.putExtra(COMMAND, RESUME);
        Intent stopIntent = new Intent(this, AudioService.class);
        stopIntent.putExtra(COMMAND, STOP);

        PendingIntent pendingPlayIntent = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent pendingPauseIntent = PendingIntent.getService(this, 1, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent pendingResumeIntent = PendingIntent.getService(this, 2, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent pendingStopIntent = PendingIntent.getService(this, 3, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproducción de Texto")
                .setContentText(isSpeaking ? "Reproduciendo texto en segundo plano" : "Texto pausado")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent);

        if (isSpeaking) {
            notificationBuilder
                    .addAction(R.drawable.baseline_pause_circle_outline_24, "Pause", pendingPauseIntent)
                    .addAction(R.drawable.ic_stop, "Stop", pendingStopIntent);
        } else {
            notificationBuilder
                    .addAction(R.drawable.baseline_play_circle_outline_24, "Play", pendingPlayIntent)
                    .addAction(R.drawable.baseline_restart_alt_24, "Resume", pendingResumeIntent)
                    .addAction(R.drawable.ic_stop, "Stop", pendingStopIntent);
        }

        Notification notification = notificationBuilder.build();
        startForeground(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Audio Play Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}
