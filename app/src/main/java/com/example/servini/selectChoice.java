package com.example.servini;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ImageView;

public class selectChoice extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_choice);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ((Button) findViewById(R.id.utilisateur)).setOnClickListener(this);
        ((Button) findViewById(R.id.employe)).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
       Intent intent;
       if (v.getId()==R.id.utilisateur)
       {
           intent = new Intent(this, Login.class);
           intent.putExtra("mode","utilisateur");
           startActivity(intent);
       } else if (v.getId()==R.id.employe) {
           intent = new Intent(this, Login.class);
           intent.putExtra("mode","employe");
           startActivity(intent);
       }
    }
}