package com.example.servini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class historique extends AppCompatActivity {
ImageView icone;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        icone = findViewById(R.id.icone);
        user = user = getIntent().getStringExtra("user");
        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(historique.this, MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}