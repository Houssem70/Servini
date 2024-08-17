package com.example.servini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacter extends AppCompatActivity implements View.OnClickListener{
EditText sujet,message;
String user;
ImageView img;
    FirebaseDatabase database;
    DatabaseReference reference;
    HelperClass userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacter);
        user = getIntent().getStringExtra("user");
        database = FirebaseDatabase.getInstance("https://servini-f101e-default-rtdb.europe-west1.firebasedatabase.app/");
        Button sendEmailButton = findViewById(R.id.envoyer);
        img = findViewById(R.id.img);
        sujet = findViewById(R.id.sujet);
        message = findViewById(R.id.messageInput);
        sendEmailButton.setOnClickListener(this);
        img.setOnClickListener(this);
    }


        private void sendEmail() {
            reference = database.getReference("users");
            Toast.makeText(Contacter.this, user, Toast.LENGTH_LONG).show();
            // Recipient email address
            String[] recipients = {"timmyfortest47@gmail.com"};

            // Subject of the email
            String subject = sujet.getText().toString().trim();

            // Body of the email
            String body = message.getText().toString().trim();

            // Create an Intent with ACTION_SENDTO to launch the email client
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);

            // Verify that there is an email client available to handle the intent
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.envoyer) {
          sendEmail();
        } else if (v.getId()==R.id.img){
            Intent intent =new Intent(Contacter.this, MainActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
            }

    }
}