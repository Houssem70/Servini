package com.example.servini;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home); //set home item selected by default

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.home)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_home()).commit();
                return true;
            } else if (item.getItemId()==R.id.profil) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_home()).commit();
                return true;
            } else if (item.getItemId()==R.id.message) {
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_home()).commit();
                return true;
            }
            else {
                return false;
            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nav_home)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new fragment_home()).commit();
        } else if (item.getItemId()==R.id.nav_profil) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
        } else if (item.getItemId()==R.id.Contactez_nous) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new contactez_nous_fragment()).commit();
        } else if (item.getItemId()==R.id.historique) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
        } else if (item.getItemId()==R.id.Déconnectez) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}