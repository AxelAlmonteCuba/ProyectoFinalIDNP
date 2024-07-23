package com.example.proyectofinalplataformas.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinalplataformas.CSV.LectorCSV;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.Service.AudioService;

import java.lang.reflect.Field;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class DescripcionPinturaFragment extends Fragment {
    private static final String ARG_PARAM1 = "nombrePintura";
    private String nombre;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;
    private static final String CHANNEL_ID = "pintura_notifications";
    private static final int NOTIFICATION_ID = 1;

    public DescripcionPinturaFragment() {
        // Required empty public constructor
    }

    public static DescripcionPinturaFragment newInstance(String nombrePintura) {
        DescripcionPinturaFragment fragment = new DescripcionPinturaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nombrePintura);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_PARAM1);
        }
        createNotificationChannel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            }
        }

        View view = inflater.inflate(R.layout.fragment_descripcion_pintura, container, false);
        LectorCSV lectorCSV = new LectorCSV(getActivity());
        Pintura pintura = lectorCSV.obtenerPinturaPorTitulo(nombre);
        ImageView btnPlayAudio = view.findViewById(R.id.btnPlayAudio);

        if (pintura != null) {
            Log.d("descriotion", nombre);
            TextView tituloTextView = view.findViewById(R.id.txtTituloPinturaDescripcion);
            TextView autorTextView = view.findViewById(R.id.txtArtistaDesc);
            TextView añoTextView = view.findViewById(R.id.txtFechaDesc);
            TextView descripcionTextView = view.findViewById(R.id.txtDescripcionDesc);

            tituloTextView.setText(pintura.getTitulo());
            autorTextView.setText(pintura.getAutor());
            añoTextView.setText(String.valueOf(pintura.getAño()));
            descripcionTextView.setText(pintura.getDescripcion());
            logAllDrawableIds();
        }

        btnPlayAudio.setOnClickListener(v -> {
            String texto = "Título: " + pintura.getTitulo() + ", Autor: " + pintura.getAutor() + ", Año: " + pintura.getAño() + ", Descripción: " + pintura.getDescripcion();
            iniciarServicioAudio(texto);
            mostrarNotificacion(texto);
        });

        return view;
    }

    private void mostrarNotificacion(String texto) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification) // Asegúrate de tener un icono de notificación en tus recursos
                .setContentTitle("Detalles de la Pintura")
                .setContentText(texto)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Pintura Notifications";
            String description = "Canal para notificaciones de pinturas";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void logAllDrawableIds() {
        Field[] fields = R.drawable.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                int drawableId = field.getInt(null);
                String name = field.getName();
                Log.d("DrawableID", "Name: " + name + " ID: " + drawableId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void iniciarServicioAudio(String texto) {
        Intent intent = new Intent(getContext(), AudioService.class);
        intent.putExtra(AudioService.TEXT, texto);
        intent.putExtra(AudioService.COMMAND, AudioService.PLAY);
        ContextCompat.startForegroundService(requireContext(), intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
            } else {
                // Permiso denegado
            }
        }
    }
}
