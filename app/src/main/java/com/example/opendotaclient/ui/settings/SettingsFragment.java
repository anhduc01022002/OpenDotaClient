package com.example.opendotaclient.ui.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opendotaclient.R;

public class SettingsFragment extends AppCompatActivity {
    public SettingsFragment(){
        //Required empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
    }
}