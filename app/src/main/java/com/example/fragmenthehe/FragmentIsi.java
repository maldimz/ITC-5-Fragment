package com.example.fragmenthehe;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.app.DialogFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentIsi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentIsi extends Fragment {

    float berat, tinggi, bmi;
    boolean check;

    TextView tvUsername;
    int[] editTextId = {
            R.id.et_berat,
            R.id.et_tinggi
    };

    int[] buttonId = {
            R.id.btn_test,
            R.id.btn_logut
    };

    int[] textViewId = {
            R.id.tv_userDialog,
            R.id.tv_hasilBmi,
            R.id.tv_klasifikasi
    };
    ArrayList<EditText> editTexts = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<TextView> textViews = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentIsi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentIsi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentIsi newInstance(String param1, String param2) {
        FragmentIsi fragment = new FragmentIsi();
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
        View view = inflater.inflate(R.layout.fragment_isi, container, false);

        tvUsername = view.findViewById(R.id.tv_user);
        for (int value : buttonId) buttons.add(view.findViewById(value));
        for (int value : editTextId) editTexts.add(view.findViewById(value));
        Data data = new Data();
        tvUsername.setText(data.getUsername());
        if(data.getBerat().equals("")||data.getTinggi().equals("")){
            Log.d("CEK DATA", "Data Masih Kosong");
        }else{
            Log.d("CEK DATA", "Data Sudah Ada");
            editTexts.get(0).setText(data.getBerat());
            editTexts.get(1).setText(data.getTinggi());
        }
        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CEK BUTTON", "TEST BERJALAN");

                try {
                    check = true;
                    if(editTexts.get(0).getText().toString().equals("") || editTexts.get(1).getText().toString().equals(""))
                        throw new NullPointerException();
                    else{
                        berat = Float.parseFloat(editTexts.get(0).getText().toString());
                        tinggi = Float.parseFloat(editTexts.get(1).getText().toString());
                    }
                } catch (NumberFormatException nfe) {
                    check = false;
                    Toast.makeText(getActivity(), "Harus Angka!", Toast.LENGTH_SHORT).show();
                    Log.d("CEK INPUT", "Nilai harus integer");
                } catch(NullPointerException npe){
                    check = false;
                    Log.d("CEK INPUT", "Harus Diisi");
                    Toast.makeText(getActivity(), "Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                }

                if(check==true){
                    data.setTinggi(String.valueOf(tinggi));
                    tinggi = tinggi/100;

                    data.setBerat(String.valueOf(berat));
                    bmi = berat / (tinggi*tinggi);
                    data.setBmi(bmi);
                    if (bmi < 18.5){
                        data.setKlasifikasi("Underweight");
                    } else if (bmi >= 18.5 && bmi <=24.9){
                        data.setKlasifikasi("Normal range");
                    } else if (bmi >= 25 && bmi <= 29.9){
                        data.setKlasifikasi("Pre-obese");
                    } else if (bmi >= 30 && bmi <= 34.9){
                        data.setKlasifikasi("Obese I");
                    } else if (bmi >= 35 && bmi <= 39.9){
                        data.setKlasifikasi("Obese II");
                    } else if (bmi >= 40){
                        data.setKlasifikasi("Obese III");
                    }
                    Log.d("CEK DATA", "UPLOADED");
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), R.style.Transparent_Dialog);
                    View dialogView = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
                    dialogBuilder.setView(dialogView);
                    final AlertDialog dialog = dialogBuilder.create();
                    dialog.show();
                    Log.d("CEK DIALOG", "Dialog Fragment Berjalan");
                    for (int value : textViewId) textViews.add(dialogView.findViewById(value));
                    textViews.get(0).setText(data.getUsername());
                    textViews.get(1).setText("BMI = " + String.valueOf(data.getBmi()));
                    textViews.get(2).setText("Klasifikasi Anda : " + data.getKlasifikasi());
                    Log.d("CEK DIALOG", "Dialog Fragment Data Update");
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