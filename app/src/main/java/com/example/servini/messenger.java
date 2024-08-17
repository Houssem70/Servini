package com.example.servini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class messenger extends AppCompatActivity {
    String userName;
    ImageView retourner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        userName = getIntent().getStringExtra("userName");
        retourner = (ImageView) findViewById(R.id.retourner);
        retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(messenger.this, ProfilEmployee.class);
                intent.putExtra("user", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}