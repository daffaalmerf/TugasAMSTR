package com.example.tugasamstr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tugasamstr.R;
import com.example.tugasamstr.fragment.ProfileFragment;
import com.example.tugasamstr.fragment.TokoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.mainNav);

        NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

}
