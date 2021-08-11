package com.example.fragmenthehe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {

    private String username, password, inputUname, inputPassword;
    int[] buttonID = {
            R.id.btn_login,
            R.id.btn_daftar_login
    };
    int[] editTextId = {
            R.id.et_username_login,
            R.id.et_password_login
    };

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        for (int value : buttonID) buttons.add(view.findViewById(value));
        for (int value : editTextId) editTexts.add(view.findViewById(value));

            buttons.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data data = new Data();
                    username = data.getUsername();
                    password = data.getPassword();
                    Log.d("CEK DATA", "Data Terload");

                    try{
                        inputUname = editTexts.get(0).getText().toString();
                        inputPassword = editTexts.get(1).getText().toString();
                        if(inputUname.equals("")||inputPassword.equals(""))
                            throw new NullPointerException();
                        else if(inputUname.equals(username)&&inputPassword.equals(password)){
                            Log.d("CEK BUTTON", "LOGIN");
                            Toast.makeText(getActivity(), "Masuk", Toast.LENGTH_SHORT).show();

                            FragmentIsi fragmentIsi = new FragmentIsi();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fl_mainFrame, fragmentIsi);
                            transaction.commit();
                        }else{
                            Toast.makeText(getActivity(), "Username / Password Salah!", Toast.LENGTH_SHORT).show();
                        }
                    }catch(NullPointerException npe){
                        Log.d("CEK INPUT", "Harus Diisi");
                        Toast.makeText(getActivity(), "Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            buttons.get(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentDaftar fragmentDaftar = new FragmentDaftar();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fl_mainFrame, fragmentDaftar);
                    transaction.commit();
                    Log.d("CEK BUTTON", "DAFTAR");
                }
            });
            // Inflate the layout for this fragment
            return view;
    }
}