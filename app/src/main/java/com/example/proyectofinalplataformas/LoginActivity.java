package com.example.proyectofinalplataformas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("nombres", user.getDisplayName());
                    startActivity(intent);
                    finish(); // Terminar esta actividad para que el usuario no pueda volver atrás
                } else {
                    // El usuario no está autenticado, mostrar la interfaz de inicio de sesión
                    // Aquí puedes mostrar un fragmento de inicio de sesión o un diseño de inicio de sesión
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Agregar el listener de autenticación en el inicio de la actividad
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Eliminar el listener de autenticación cuando la actividad se detiene
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    // Otros métodos de inicio de sesión, como signInWithEmailAndPassword(), signInWithGoogle(), etc.
}
