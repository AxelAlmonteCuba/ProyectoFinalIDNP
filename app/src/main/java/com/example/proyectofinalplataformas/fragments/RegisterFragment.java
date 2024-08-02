package com.example.proyectofinalplataformas.fragments;

import android.content.Intent;
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
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.ViewModel.ShareViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private LoginFragment loginFragment = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RC_SIGN_IN_REGISTER = 9002;


    private String mParam1;
    private String mParam2;
    private ShareViewModel shareViewModel;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection = firestore.collection("users");
    private GoogleSignInClient googleSignInClient;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.id_client))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), signInOptions);

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        TextView txtLogin = view.findViewById(R.id.txtLogin);
        Button btnRegistrate = view.findViewById(R.id.btnRegitrarse);
        Button btnGoogleRegister = view.findViewById(R.id.btnGoogleRegister);
        btnRegistrate.setOnClickListener(v -> {
            TextView edtNombresR = view.findViewById(R.id.edtNombresR);
            TextView edtApellidosR = view.findViewById(R.id.edtApellidosR);
            TextView edtCorreoR = view.findViewById(R.id.edtCorreoR);
            TextView edtContrasenaR = view.findViewById(R.id.edtContrasenaR);

            final String nombres = edtNombresR.getText().toString();
            final String apellidos = edtApellidosR.getText().toString();
            final String correo = edtCorreoR.getText().toString();
            final String contrasena = edtContrasenaR.getText().toString();

            if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(requireActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("nombres", nombres);
                                    userMap.put("apellidos", apellidos);
                                    userMap.put("correo", correo);

                                    usersCollection.document(user.getUid())
                                            .set(userMap)
                                            .addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "User data saved successfully");
                                                        Toast.makeText(requireActivity(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                                                        loginFragment = LoginFragment.newInstance("","");
                                                        LoadFragment(loginFragment);
                                                    } else {
                                                        Log.e(TAG, "Error al registrar la información del usuario", task.getException());
                                                        Toast.makeText(requireActivity(), "Error al registrar la información del usuario", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Log.e(TAG, "User is null after registration");
                                    Toast.makeText(requireActivity(), "Error al registrar", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.e(TAG, "Error al registrar", task.getException());
                                Toast.makeText(requireActivity(), "Error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });

        txtLogin.setOnClickListener(v -> {
            loginFragment = LoginFragment.newInstance("","");
            LoadFragment(loginFragment);
        });

        btnGoogleRegister.setOnClickListener(v -> {
            signInWithGoogleForRegister();
        });

        return view;
    }

    private void signInWithGoogleForRegister() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_REGISTER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado devuelto desde el intento de GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_REGISTER) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In fue exitoso, registrar con Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogleForRegister(account.getIdToken());
            } catch (ApiException e) {
                // Error en Google Sign In
                Log.w(TAG, "Google sign in failed for registration", e);
            }
        }
    }

    private void firebaseAuthWithGoogleForRegister(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso con Google, obtener el usuario actual
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                // Guardar los datos del usuario en Firestore
                                Map<String, Object> userData = new HashMap<>();
                                userData.put("nombre", user.getDisplayName());
                                userData.put("correo", user.getEmail());

                                firestore.collection("usuarios").document(user.getUid())
                                        .set(userData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "Datos del usuario guardados en Firestore.");
                                                // Aquí puedes navegar a la siguiente pantalla o realizar otras acciones según necesites
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e(TAG, "Error al guardar los datos del usuario en Firestore.", e);
                                            }
                                        });
                            }
                        } else {
                            // Si el inicio de sesión falla, mostrar un mensaje al usuario
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainConteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d(TAG, "Fragment loaded successfully");
    }
}
