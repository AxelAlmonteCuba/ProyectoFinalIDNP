package com.example.proyectofinalplataformas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectofinalplataformas.Entitys.AccountEntity;
import com.example.proyectofinalplataformas.R;
import com.example.proyectofinalplataformas.ViewModel.ShareViewModel;


public class RegisterFragment extends Fragment {
    private LoginFragment loginFragment = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ShareViewModel shareViewModel;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        TextView txtLogin = view.findViewById(R.id.txtLogin);
        Button btnRegistrate = view.findViewById(R.id.btnRegitrarse);

        btnRegistrate.setOnClickListener(v -> {
            TextView edtNombresR = view.findViewById(R.id.edtNombresR);
            TextView edtApellidosR = view.findViewById(R.id.edtApellidosR);
            TextView edtCorreoR = view.findViewById(R.id.edtCorreoR);
            TextView edtContrasenaR = view.findViewById(R.id.edtContrasenaR);
            AccountEntity account = new AccountEntity();
            account.setFirstname(edtNombresR.getText().toString());
            account.setLastname(edtApellidosR.getText().toString());
            account.setEmail(edtCorreoR.getText().toString());
            account.setPassword(edtContrasenaR.getText().toString());
            shareViewModel.SetAccount(account);

            loginFragment = LoginFragment.newInstance("", "");
            LoadFragment(loginFragment);

        });
        txtLogin.setOnClickListener(v -> {
            loginFragment = LoginFragment.newInstance("", "");
            LoadFragment(loginFragment);

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