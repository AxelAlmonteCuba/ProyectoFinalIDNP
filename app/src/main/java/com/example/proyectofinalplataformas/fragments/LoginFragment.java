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

import com.example.proyectofinalplataformas.Entitys.AccountEntity;
import com.example.proyectofinalplataformas.HomeActivity;
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.ViewModel.ShareViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.concurrent.Executor;


public class LoginFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private Button btnLogin  ;
    private TextView edtCorreo;
    private TextView edtPassword;
    private TextView txtRegister;
    private ShareViewModel shareViewModel;
    private RegisterFragment registerFragment = null;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        edtCorreo = view.findViewById(R.id.edtCorreo);
        edtPassword = view.findViewById(R.id.edtPassword);
        txtRegister = view.findViewById(R.id.txtRegistrate);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFragment = RegisterFragment.newInstance("","");
                LoadFragment(registerFragment);
            }
        });
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        /*btnLogin.setOnClickListener(v ->{
            shareViewModel.getAccount().observe(getViewLifecycleOwner(), accountEntity->{
                if (((edtCorreo.getText().toString().equals(accountEntity.getEmail()) && edtPassword.getText().toString().equals(accountEntity.getPassword())))) {
                    Toast.makeText(getActivity().getApplicationContext(), "Bienvenido a mi App", Toast.LENGTH_SHORT).show();
                    Log.d("Login", "Bienvenido a mi App");
                    *//*Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra("ACCOUNT", accontEntityString);
                    startActivity(intent);*//*
                    Intent intent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                    Gson gson = new Gson();
                    String accountString = gson.toJson(accountEntity);
                    intent.putExtra("account",accountString);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error en la autenticacion", Toast.LENGTH_SHORT).show();
                    Log.d("Login ", "Error en la autenticacion");
                }
            });

                *//*Intent intent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                startActivity(intent);*//*

        });*/

        btnLogin.setOnClickListener(v -> {
            final String correo = edtCorreo.getText().toString();
            final String contrasena = edtPassword.getText().toString();

            if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
                Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
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
                                                        String nombre = task.getResult().getString("nombres");
                                                        String correo = task.getResult().getString("correo");
                                                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                        intent.putExtra("nombres", nombre);
                                                        intent.putExtra("correo", correo);
                                                        startActivity(intent);

                                                    } else {
                                                        Toast.makeText(getActivity(), "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            } else {
                                Toast.makeText(getActivity(), "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        return view;
    }
    public void LoadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainConteiner, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}