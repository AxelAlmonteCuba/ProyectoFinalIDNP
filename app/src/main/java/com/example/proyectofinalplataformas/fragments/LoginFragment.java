package com.example.proyectofinalplataformas.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalplataformas.CSV.LectorCSV;
import com.example.proyectofinalplataformas.DAO.PinturaDao;
import com.example.proyectofinalplataformas.Database.AppDatabase;
import com.example.proyectofinalplataformas.Database.DatabaseClient;
import com.example.proyectofinalplataformas.Entitys.AccountEntity;
import com.example.proyectofinalplataformas.Entitys.Pintura;
import com.example.proyectofinalplataformas.HomeActivity;
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.ViewModel.ShareViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Login Fragment";

    private String mParam1;
    private String mParam2;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private Button btnLogin;
    private Button btnGoogleLogin;
    private TextView edtCorreo;
    private TextView edtPassword;
    private TextView txtRegister;
    private ShareViewModel shareViewModel;
    private RegisterFragment registerFragment = null;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private GoogleSignInClient googleSignInClient;
    PinturaDao pinturaDao;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.id_client))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), signInOptions);





        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnGoogleLogin = view.findViewById(R.id.btnGoogleLogin);
        edtCorreo = view.findViewById(R.id.edtCorreo);
        edtPassword = view.findViewById(R.id.edtPassword);
        txtRegister = view.findViewById(R.id.txtRegistrate);

        txtRegister.setOnClickListener(v -> {
            registerFragment = RegisterFragment.newInstance("","");
            LoadFragment(registerFragment);
        });

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        btnLogin.setOnClickListener(v -> {
            final String correo = edtCorreo.getText().toString();
            final String contrasena = edtPassword.getText().toString();

            if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(requireActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    firestore.collection("users").document(user.getUid()).get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful() && task.getResult() != null) {
                                                        String nombres = task.getResult().getString("nombres");
                                                        String apellidos = task.getResult().getString("apellidos");
                                                        String correo = task.getResult().getString("email");

                                                        Log.d("LoginFragment", "Task completed, checking if fragment is added");

                                                        if (isAdded() && getActivity() != null) { // Verificar si el Fragment está añadido y el Activity no es nulo
                                                            Log.d("LoginFragment", "Fragment is added, creating intent");
                                                            Intent intent = new Intent(requireActivity(), HomeActivity.class);
                                                            intent.putExtra("nombres", nombres);
                                                            Log.d("login", nombres);
                                                            intent.putExtra("apellidos", apellidos);
                                                            intent.putExtra("correo", correo);
                                                            startActivity(intent);
                                                        } else {
                                                            Log.e("LoginFragment", "Fragment is not added or Activity is null");
                                                        }
                                                    } else {
                                                        Toast.makeText(requireActivity(), "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            } else {
                                Toast.makeText(requireActivity(), "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });



        btnGoogleLogin.setOnClickListener(v -> signInWithGoogle());

        return view;
    }

    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainConteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                firestore.collection("users").document(user.getUid()).get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful() && task.getResult() != null) {
                                                    String nombres = task.getResult().getString("nombres");
                                                    String apellidos = task.getResult().getString("apellidos");
                                                    String correo = task.getResult().getString("email");

                                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                    intent.putExtra("nombres", nombres);
                                                    intent.putExtra("apellidos", apellidos);
                                                    intent.putExtra("correo", correo);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(getActivity(), "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}
