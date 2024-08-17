package com.example.servini;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class animation extends AppCompatActivity {

    private static int SPLACH_SCREEN=4000;
    Animation topanim,buttomanim;
    TextView slogan;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        buttomanim= AnimationUtils.loadAnimation(this,R.anim.buttom_anim);

        slogan=(TextView)findViewById(R.id.servini);
        logo=(ImageView)findViewById(R.id.logo);

        logo.setAnimation(topanim);
        slogan.setAnimation(buttomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(animation.this,selectChoice.class);
                startActivity(intent);
                finish();
            }
        },SPLACH_SCREEN);
    }
}