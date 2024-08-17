package com.example.servini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ((Button) findViewById(R.id.login)).setOnClickListener(this);
        ((Button) findViewById(R.id.signup)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String mode = getIntent().getStringExtra("mode");
        if(v.getId()==R.id.login)
        {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId()==R.id.signup) {
            if (mode == "utilisateur") {
                intent = new Intent(this, inscription.class);
                startActivity(intent);
            } else if (mode == "employe") {
                intent = new Intent(this, inscription2.class);
                startActivity(intent);
            }
        }
    }

}