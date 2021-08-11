package com.example.fragmenthehe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentLogin mFragmentLogin = new FragmentLogin();
        Fragment fragmentLogin = mFragmentManager.findFragmentByTag(FragmentLogin.class.getSimpleName());

        if (!(fragmentLogin instanceof FragmentLogin)) {
            mFragmentManager.beginTransaction()
                    .add(R.id.fl_mainFrame, mFragmentLogin, FragmentLogin.class.getSimpleName())
                    .commit();
        }
    }
}