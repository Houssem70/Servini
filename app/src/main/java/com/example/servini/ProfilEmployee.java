package com.example.servini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfilEmployee extends AppCompatActivity implements View.OnClickListener {
    ImageView arrow, phone, msg, photo;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView nom, adr, role, desc;
    private static final int REQUEST_CALL_PERMISSION = 1;
    Employe employeData;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_employee);
        arrow = (ImageView) findViewById(R.id.arrow);
        arrow.setOnClickListener(this);
        nom = (TextView) findViewById(R.id.nom);
        adr = (TextView) findViewById(R.id.lieu);
        role = (TextView) findViewById(R.id.role);
        desc = (TextView) findViewById(R.id.desc);
        phone = (ImageView) findViewById(R.id.iconphone);
        phone.setOnClickListener(this);
        msg = (ImageView) findViewById(R.id.iconmessage);
        msg.setOnClickListener(this);
        photo = (ImageView) findViewById(R.id.photoInput);
        database = FirebaseDatabase.getInstance("https://servini-f101e-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference("employes");
        userName = getIntent().getStringExtra("userName");
        Toast.makeText(ProfilEmployee.this, userName, Toast.LENGTH_LONG).show();
        reference.orderByChild("username").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    employeData = userSnapshot.getValue(Employe.class);
                    nom.setText(employeData.getUsername());
                    adr.setText(employeData.getAdresse());
                    role.setText(employeData.getMétier());
                    desc.setText(employeData.getDescription());
                    Picasso.get().load(employeData.getImageUri()).transform(new CircularMarkerUtils.CircleTransformation(120)).fit().into(photo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.arrow) {
            String choix = getIntent().getStringExtra("choix");
            Intent intent = new Intent(ProfilEmployee.this, Map.class);
            intent.putExtra("choix", choix);
            intent.putExtra("user", userName);
            startActivity(intent);

        } else if (view.getId() == R.id.iconphone) {
            makePhoneCall();

        } else if (view.getId() == R.id.iconmessage){
            Intent intent = new Intent(ProfilEmployee.this, messenger.class);
            intent.putExtra("user", userName);
            startActivity(intent);
            finish();
        }

    }

    private void makePhoneCall() {

        // Check for permission before making the call
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Permission already granted, make the call
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + employeData.getPhone()));
            startActivity(callIntent);
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                makePhoneCall();
            }
        }
    }
}
