package com.example.servini;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfilUtilisateur extends AppCompatActivity implements View.OnClickListener {
    Button btnPhotoProfil;
    Button btnAdresse;
    Button btnNumTel;
    Button btnEmail;
    Button btnNomPrenom;
    ImageView img, fleche;
    FirebaseDatabase database;
    DatabaseReference reference;
    HelperClass userData,user;
    TextView nom, adr, num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilisateur);
        // Ajouter des boutons pour chaque modification
        (btnNomPrenom = (Button) findViewById(R.id.btnModifierNomPrenom)).setOnClickListener(this);
        (btnEmail = (Button) findViewById(R.id.btnModifierEmail)).setOnClickListener(this);
        (btnNumTel = (Button) findViewById(R.id.btnModifierNumTel)).setOnClickListener(this);
        (btnAdresse = (Button) findViewById(R.id.btnModifierAdresse)).setOnClickListener(this);
        (btnPhotoProfil = (Button) findViewById(R.id.btnModifierPhotoProfil)).setOnClickListener(this);
        user = (HelperClass) getIntent().getSerializableExtra("userdata");
        nom = (TextView) findViewById(R.id.nom);
        adr = (TextView) findViewById(R.id.lieu);
        num = (TextView) findViewById(R.id.num);
        img = findViewById(R.id.photoInput);
        fleche = findViewById(R.id.fleche);
        database = FirebaseDatabase.getInstance("https://servini-f101e-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference("users");
        reference.orderByChild("username").equalTo(user.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    userData = userSnapshot.getValue(HelperClass.class);
                    nom.setText(userData.getUsername());
                    adr.setText(userData.getAdresse());
                    num.setText(userData.getPhone());
                    Picasso.get().load(userData.getImageUri()).transform(new CircularMarkerUtils.CircleTransformation(110)).fit().into(img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void modifier(int layoutResId, int idbtn,int c) {
        // Inflate the custom layout
        View dialogView = LayoutInflater.from(ProfilUtilisateur.this).inflate(layoutResId, null);

        // Set up the second dialog
        Dialog dialog = new Dialog(ProfilUtilisateur.this);
        dialog.setContentView(dialogView); // Set the inflated view

        // Set background drawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(ProfilUtilisateur.this.getDrawable(R.drawable.background2));
        }

        // Set dialog layout parameters
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        // Find the button in the inflated view
        Button btnmodifi = dialogView.findViewById(idbtn);

        // Set click listener for the button
        btnmodifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modifier2(c);
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }
private void modifier2(int c){
    if(c==0){
    EditText nom=findViewById(R.id.nomInput);
    String n=nom.getText().toString().trim();
    userData.setUsername(n);
    reference.setValue(userData)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ProfilUtilisateur.this, "Nom modifié", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle failure
                    Toast.makeText(ProfilUtilisateur.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
    } else if (c==1) {
        EditText email=findViewById(R.id.email);
        String n=email.getText().toString().trim();
        userData.setEmail(n);
        reference.setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfilUtilisateur.this, "Email modifié", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(ProfilUtilisateur.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    } else if (c==2) {
        EditText num=findViewById(R.id.numtelInput);
        String n=num.getText().toString().trim();
        userData.setPhone(n);
        reference.setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfilUtilisateur.this, "Num modifié", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(ProfilUtilisateur.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    } else if (c==3) {
        EditText adr=findViewById(R.id.locationlInput);
        String n=adr.getText().toString().trim();
        userData.setAdresse(n);
        reference.setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfilUtilisateur.this, "Adresse modifiée", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(ProfilUtilisateur.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.btnModifierNomPrenom) {
            modifier(R.layout.activity_modifnom, R.id.btn_modif,0);

        } else if (v.getId() == R.id.btnModifierEmail) {

            modifier(R.layout.activity_modifemail, R.id.btn_modif2,1);

        } else if (v.getId() == R.id.btnModifierNumTel) {

            modifier(R.layout.activity_modifnum, R.id.btnModifierNumTel,2);

        } else if (v.getId() == R.id.btnModifierAdresse) {

            modifier(R.layout.modifadresse, R.id.btnModifierAdresse,3);

        } else if (v.getId() == R.id.btnModifierPhotoProfil) {

            modifier(R.layout.modifphoto, R.id.btnModifierPhotoProfil,4);

        } else if (v.getId() == R.id.fleche) {
            intent = new Intent(ProfilUtilisateur.this, MainActivity.class);
            intent.putExtra("user", user.getUsername());
            startActivity(intent);
        }
    }
}