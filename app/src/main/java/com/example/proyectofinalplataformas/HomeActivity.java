package com.example.proyectofinalplataformas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectofinalplataformas.fragments.FavoritosFragment;
import com.example.proyectofinalplataformas.fragments.GaleriasFragment;
import com.example.proyectofinalplataformas.fragments.HomeFragment;
import com.example.proyectofinalplataformas.fragments.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private HomeFragment homeFragment = null;
    private FavoritosFragment favoritosFragment = null;
    private MapFragment mapFragment = null;
    private GaleriasFragment galeriasFragment = null;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent1 = getIntent();
        String nombres = intent1.getStringExtra("nombres");
        String apellidos = intent1.getStringExtra("apellidos");
        String correo = intent1.getStringExtra("correo");
        // Revisión y corrección de Log.d para evitar NullPointerException
        Log.d("HomeActivity", "Nombre: " + (nombres != null ? nombres : "No disponible"));
        Log.d("HomeActivity", "Apellidos: " + (apellidos != null ? apellidos : "No disponible"));
        Log.d("HomeActivity", "Correo: " + (correo != null ? correo : "No disponible"));


        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.btnNavigation);
        TextView txtTitle = findViewById(R.id.txtTitle);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        ImageView imgLogout = findViewById(R.id.imgLogout);

        imgLogout.setOnClickListener(v -> {
            firebaseAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);  // Asumiendo que LoginActivity inicia LoginFragment
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Finaliza
        });
        String nombre = getIntent().getStringExtra("nombres");

        // Configurar el fragmento inicial
        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance(nombre);
            LoadFragment(homeFragment);
        }


        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_home){
                    txtTitle.setText("Home");
                    homeFragment = HomeFragment.newInstance(nombres);
                    LoadFragment(homeFragment);
                } else if (menuItem.getItemId() == R.id.menu_galerias) {
                    txtTitle.setText("Galerias");
                    galeriasFragment = GaleriasFragment.newInstance("","");
                    LoadFragment(galeriasFragment);
                } else if (menuItem.getItemId() == R.id.menu_mapa) {
                    txtTitle.setText("Mapa");
                    mapFragment = MapFragment.newInstance("","");
                    LoadFragment(mapFragment);
                } else if (menuItem.getItemId() == R.id.menu_favoritos) {
                    txtTitle.setText("Favoritos");
                    favoritosFragment = FavoritosFragment.newInstance("","");
                    LoadFragment(favoritosFragment);
                }
            }
        });
    }

    private void LoadFragment(Fragment fragment){
        if(fragmentManager != null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Home_Conteiner, fragment);
            fragmentTransaction.commit();
        }
    }
}
