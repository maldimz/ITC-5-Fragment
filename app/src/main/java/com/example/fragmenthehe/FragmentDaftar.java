package com.example.fragmenthehe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDaftar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDaftar extends Fragment {
    String username, password, confirmPassword;

    int[] editTextId = {
            R.id.et_username_daftar,
            R.id.et_password_daftar,
            R.id.et_repassword_daftar
    };

    int[] buttonId = {
            R.id.btn_daftar_daftar,
            R.id.btn_kembali
    };

    ArrayList<EditText> editTexts = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDaftar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDaftar.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDaftar newInstance(String param1, String param2) {
        FragmentDaftar fragment = new FragmentDaftar();
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
        View view = inflater.inflate(R.layout.fragment_daftar, container, false);

        for (int value : editTextId) editTexts.add(view.findViewById(value));
        for (int value : buttonId) buttons.add(view.findViewById(value));

        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                try{
                    username = editTexts.get(0).getText().toString();
                    password = editTexts.get(1).getText().toString();
                    confirmPassword = editTexts.get(2).getText().toString();
                    if(username.equals("")||password.equals("")||confirmPassword.equals(""))
                        throw new NullPointerException();
                }catch(NullPointerException npe){
                    Log.d("CEK INPUT", "Harus Diisi");
                    Toast.makeText(getActivity(), "Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirmPassword)){
                    data.setUsername(username);
                    data.setPassword(password);
                    Log.d("CEK DAFTAR", "Data Tersimpan");
                    Toast.makeText(getActivity(), "Berhasil!", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("CEK DAFTAR", "GAGAL");
                    Toast.makeText(getActivity(), "Password Salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLogin fragmentLogin = new FragmentLogin();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_mainFrame, fragmentLogin);
                transaction.commit();
                Log.d("CEK BUTTON", "KEMBALI LOGIN");
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}