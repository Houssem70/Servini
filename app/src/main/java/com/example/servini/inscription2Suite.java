package com.example.servini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inscription2Suite extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2_suite);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ((Button) findViewById(R.id.inscrire)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}